package umc.spring_study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring_study.domain.common.BaseEntity;
import umc.spring_study.domain.mapping.MemberMission;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String missionSpec;

    @Column(nullable = false)
    private Integer reward;

    @Column(nullable = false)
    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", missionSpec='" + missionSpec + '\'' +
                ", reward='" + reward + '\'' +
                ", deadline=" + deadline +
                ", store=" + (store != null ? store.getName() : "N/A") + // region의 이름 출력
                '}';
    }
}
