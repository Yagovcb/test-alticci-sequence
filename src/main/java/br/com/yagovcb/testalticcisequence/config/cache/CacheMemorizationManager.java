package br.com.yagovcb.testalticcisequence.config.cache;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@ApplicationScope
public class CacheMemorizationManager {

    private final Map<Long, BigInteger> cache = new HashMap<>();

    public void save(Long index, BigInteger value) {
        cache.put(index, value);
    }

    public BigInteger get(Long index) {
        return cache.get(
                Optional.ofNullable(index)
                        .orElseThrow(() -> new InvalidParameterException("Cannot retrieve index of null"))
        );
    }

    public String clearCache() {
        cache.clear();
        return "The cache has been clear";
    }

    public String checkCacheStr() {
        return cache.entrySet().stream()
                .map(val -> "[ " + val.getKey() + " - " + val.getValue() + " ]")
                .collect(Collectors.joining("\n"));
    }
}
