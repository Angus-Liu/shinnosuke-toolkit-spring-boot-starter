package com.angus.shinnosuke.toolkit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * Shinnosuke Toolkit Auto Configuration
 */
@Slf4j
@Configuration
public class ShinnosukeToolkitAutoConfiguration {

    public static final String BANNER_NAME = "shinnosuke-toolkit-banner.txt";

    @PostConstruct
    public void postConstruct() {
        ClassPathResource bannerResource = new ClassPathResource(BANNER_NAME);
        try (InputStreamReader inputStreamReader = new InputStreamReader(bannerResource.getInputStream());
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            bufferedReader.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ShinnosukeToolkitAutoConfiguration().postConstruct();
    }
}
