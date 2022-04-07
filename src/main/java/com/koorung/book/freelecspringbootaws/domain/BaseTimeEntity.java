package com.koorung.book.freelecspringbootaws.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * JPA Auditing : JPA에서 자체적으로 Entity들의 createdDate, modifiedDate를 관리해주는 기능
 */
@Getter
@MappedSuperclass  // 이 클래스를 상속하면 JPA에서 이 클래스의 필드들도 상속 받은 클래스의 컬럼으로 인식하게 해주는 애노테이션
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 기능 추가
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
