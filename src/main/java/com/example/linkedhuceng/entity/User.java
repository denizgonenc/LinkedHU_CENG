package com.example.linkedhuceng.entity;


import com.example.linkedhuceng.entity.relation.Follow;
import com.example.linkedhuceng.entity.relation.Like;
import com.example.linkedhuceng.entity.relation.Membership;
import com.example.linkedhuceng.security.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String about = "Hello, welcome to my profile!";
    private String countryOrRegion="";
    private String city="";
    private String phoneNumber="";
    private String linkedinUrl="";
    @ElementCollection
    private List<String> skills = new ArrayList<>();

    private int reportCount=0;

    @OneToMany(mappedBy="to")
    private List<Follow> followers;
    @OneToMany(mappedBy="from")
    private List<Follow> following;

    @OneToMany
    private List<User> blockedUsers;

    @OneToMany(mappedBy="from", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Like> likes;

    @OneToMany
    private List<Post> favouritePosts;

    @OneToMany(mappedBy= "member")
    private List<Membership> groups;

    // security
    @Enumerated
    private UserRole role;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled=false;

    // these are same as model
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private File pp;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private File cover;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Post> post;

    public User(String username, String password, String firstName, String lastName, String email, UserRole role, boolean isAccountNonExpired, boolean isAccountNonLocked,
                boolean isCredentialsNonExpired, boolean isEnabled) throws IOException {
        this.id = UUID.randomUUID();
        this.role = role;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        this.blockedUsers = new ArrayList<>();
        this.favouritePosts = new ArrayList<>();

        java.io.File ppFile = new java.io.File("src/main/webapp/resources/static/img/user-image.png");
        byte[] ppFileContent = Files.readAllBytes(ppFile.toPath());
        File commonPp = new File("commonPp", "image/png", ppFileContent);
        this.pp = commonPp;

        java.io.File coverFile = new java.io.File("src/main/webapp/resources/static/img/default-cover-page.png");
        byte[] coverFileContent = Files.readAllBytes(coverFile.toPath());
        File commonCover = new File("commonCover", "image/png", coverFileContent);
        this.cover = commonCover;
    }

    // methods
    public void favPost(Post post){
        this.favouritePosts.add(post);
    }

    public void unfavPost(Post post){
        this.favouritePosts.remove(post);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role.getGrantedAuthorities();
    }

    public void blockUser(User userToBlock){
        this.blockedUsers.add(userToBlock);
    }

    public void unblockUser(User blockedUser){
        this.blockedUsers.remove(blockedUser);
    }

    // TODO check in future
    public void report(){
        this.reportCount += 1;
    }

    // TODO check in future
    public void ban(){
        this.isAccountNonLocked = false;
    }

    public void unban(){
        this.isAccountNonLocked = true;
    }

    public void setCoverImage(File file){
        this.cover = file;
    }
    public void setPpImage(File file){
        this.pp = file;
    }
    public boolean getNotBanned(){
        return this.isAccountNonLocked;
    }
}