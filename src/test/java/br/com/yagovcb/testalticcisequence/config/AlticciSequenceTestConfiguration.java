package br.com.yagovcb.testalticcisequence.config;

import br.com.yagovcb.testalticcisequence.application.service.AlticciSequenceService;
import br.com.yagovcb.testalticcisequence.config.cache.CacheMemorizationManager;
import br.com.yagovcb.testalticcisequence.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AlticciSequenceTestConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheMemorizationManager cacheMemorizationManager;

    @Bean
    public AlticciSequenceService alticciSequenceService(){
        return new AlticciSequenceService(cacheMemorizationManager);
    }
}
