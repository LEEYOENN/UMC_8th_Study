package umc.spring_study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring_study.domain.common.BaseEntity;
import umc.spring_study.domain.mapping.MemberAgree;
import umc.spring_study.domain.mapping.MemberPrefer;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    private List<Store> storeList = new ArrayList<>();

}
