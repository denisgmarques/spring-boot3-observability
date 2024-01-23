package br.com.codify.observability;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import br.com.codify.observability.post.JsonPlaceholderService;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;

@SpringBootApplication
public class ObservabilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObservabilityApplication.class, args);
	}

	@Bean
	JsonPlaceholderService JsonPlaceholderService() {
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/posts");
		HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return proxyFactory.createClient(JsonPlaceholderService.class);
	}
	
	@Bean
	@Observed(name = "posts.load-all-posts", contextualName = "post-service.find-all")
	CommandLineRunner commandLineRunner(JsonPlaceholderService jsonPlaceholderService, ObservationRegistry observationRegistry) {
		return args -> jsonPlaceholderService.findAll();
	}

	/** Method Body WITHOUT @Observed ANNOTATION
	 * 		return args -> 
			Observation.createNotStarted("posts.load-all-posts", observationRegistry)
			.lowCardinalityKeyValue("author", "Denis Giovan Marques")
			.contextualName("post.load-all-posts")
			.observe(() -> {
				List<Post> posts = jsonPlaceholderService.findAll();
				log.info("Posts: {}", posts);
			});
		
	}

	 */
}
