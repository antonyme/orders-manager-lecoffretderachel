package com.lecoffretderachel.ordersmanager.injectionconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({EditorConfig.class, ImportConfig.class})
public class AppConfig {

}
