package model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "sungjuk")
@Data
public class SungJuk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sjno;

    @Column(length = 10, nullable = false)
    private String name;
    private Integer kor;
    private Integer eng;
    private Integer mat;
    @Column(nullable = true)
    private int tot;
    @Column(nullable = true, precision = 5, scale = 1)
    private BigDecimal avg;
    @Column(length = 1)
    private String grd;
    private Date regdate;

    // persist 호출하기전에 regdate 컬럼에 현재 날짜/시간 대입
    @PrePersist
    protected  void onCreate(){
        regdate = new Date();
    }

}
