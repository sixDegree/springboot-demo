package com.cj.autoconfig.enable;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class HelloWorldImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		System.out.println("HelloWorld--selectImports");
		return new String[] {HelloWorldConfiguration.class.getName()};
	}

}
