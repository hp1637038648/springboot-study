package com.hp.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.hp.entity.User;

@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long> {

	@Cacheable(key = "#p0", condition = "#p0.length() < 10")
	// condition参数：缓存对象的条件，非必需，也需使用SpEL表达式，只有满足表达式条件的内容才会被缓存
	// 该注解配置表示只有当第一个参数的长度小于3的时候才会被缓存
	User findByName(String name);

	User findByNameAndAge(String name, Integer age);

	@Query("select u from User u where u.name=:name")
	User findUser(@Param("name") String name);
}
