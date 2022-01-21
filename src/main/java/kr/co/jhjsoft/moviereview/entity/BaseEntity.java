package kr.co.jhjsoft.moviereview.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter

public abstract class BaseEntity {
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;
    @CreatedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
