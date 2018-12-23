package com.cj.autoconfig.condition;

import java.util.Map;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SystemPropertyCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Map<String, Object> attrs= metadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());
		String name=String.valueOf(attrs.get("name"));
		String value=String.valueOf(attrs.get("value"));
		String systemPropertyValue=System.getProperty(name);
		System.out.println("condition--"+value+" vs. "+systemPropertyValue);
		return value.equals(systemPropertyValue);
	}

}
