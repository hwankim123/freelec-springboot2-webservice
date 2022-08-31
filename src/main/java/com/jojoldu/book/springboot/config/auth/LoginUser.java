package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Argument Resolver를 통한 세션값 파라미터로 전달하기
 * LoginUser라는 어노테이션을 만든다.
 *
 * @Target : 이 어노테이션이 생성될 수 있는 위치. 코드에서는 파라미터. 메소드의 파라미터로 선언된 객체에서만 사용가능
 * @Retention : 아마 생명명기 같은거임
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
