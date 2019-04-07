package ImageHoster.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Entity annotation specifies that the corresponding class is a JPA entity
@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'users'.
// Hence the table named 'users' will be created in the database with all the columns mapped to all the
// attributes in 'User' class
@Table(name = "comments")
public class Comment {

    //@Id annotation specifies that the corresponding attribute is a primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column annotation specifies that the attribute will be mapped to the column in the database.
    //Here the column name is explicitly mentioned as 'id'
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private Date createdDate;

    //The 'comments' table is mapped to 'users' table with One:One mapping
    //cascade = CascadeType.ALL specifies that if a record in 'users' table is deleted or updated, then all the records
    //in 'comments' table associated to that particular record in 'users' table will be deleted or updated  first and
    //then the record in the 'users' table will be deleted or updated
    //FetchType is EAGER
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //Below annotation indicates that the name of the column in 'comments' table referring the primary key in
    //'users' table will be 'user_id'
    @JoinColumn(name = "user_id")
    private User user;

    //The 'comments' table is referenced by the 'images' table
    //The table (primary key) is referenced by the 'comment' field in the 'images' table
    //cascade = CascadeType.REMOVE specifies that if a record in 'images' table is deleted,
    // then all the records in 'comments' table associated to that particular record in 'images' table
    // will be deleted first and then the record in the 'images' table will be deleted
    //FetchType is LAZY
    @OneToMany(mappedBy = "image", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
