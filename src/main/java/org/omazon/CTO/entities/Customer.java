package org.omazon.CTO.entities;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 29.09.13
 * Time: 00:42
 */
@Entity
@Table(name = "customer")
@SessionScoped    //it means that this object will be accessible among users http sessions. If you
@ManagedBean
public class Customer extends UserAbstract implements Serializable {

    private static final long serialVersionUID = -3009396611049889475L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId")
    private long customerId;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;
    
    @Column(name = "psw")
    private String psw;

    @OneToMany(targetEntity = Order.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<Order>();

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    
    
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Customer)) return false;
//
//        Customer customer = (Customer) o;
//
//        if (customerId != customer.customerId) return false;
//        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;
//        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
//        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
//        if (orders != null ? !orders.equals(customer.orders) : customer.orders != null) return false;
//        if (surname != null ? !surname.equals(customer.surname) : customer.surname != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (customerId ^ (customerId >>> 32));
//        result = 31 * result + (surname != null ? surname.hashCode() : 0);
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (address != null ? address.hashCode() : 0);
//        result = 31 * result + (email != null ? email.hashCode() : 0);
//        result = 31 * result + (orders != null ? orders.hashCode() : 0);
//        return result;
//    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (int) (customerId ^ (customerId >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((psw == null) ? 0 : psw.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (customerId != other.customerId)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (psw == null) {
			if (other.psw != null)
				return false;
		} else if (!psw.equals(other.psw))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
}
