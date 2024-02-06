package ge.ibsu.demo.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "city")
public class City {
    @Id
    @SequenceGenerator(name = "city_city_id_seq", sequenceName = "city_city_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_city_id_seq")
    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "city")
    private String city;

    @JoinColumn(name = "address_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}