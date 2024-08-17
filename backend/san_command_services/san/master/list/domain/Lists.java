package com.ponsun.san.master.list.domain;




import com.ponsun.san.master.list.request.CreateListRequest;
import com.ponsun.san.master.list.request.UpdateListRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "List")
public class Lists {
    private static final long serialVersionUID = 1L;

    @Id
    //@Column(name = "id",nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id;


    @Column(name = "PrimaryKey")
    private String PrimaryKey;

    @Column(name ="ID")
    private String ID;

    @Column(name = "Text")
    private String Text;

    @Column(name = "FK_ListValues")
    private String FK_ListValues;

    public static Lists create(final CreateListRequest createListRequest){
        final Lists Lists = new Lists();
        Lists.setPrimaryKey(createListRequest.getPrimaryKey());
        Lists.setID(createListRequest.getID());
        Lists.setText(createListRequest.getText());
        Lists.setFK_ListValues(createListRequest.getFK_ListValues());
        return Lists;
    }
    public void update(UpdateListRequest updateListRequest){
        this.setPrimaryKey(updateListRequest.getPrimaryKey());
        this.setID(updateListRequest.getID());
        this.setText(updateListRequest.getText());
        this.setFK_ListValues(updateListRequest.getFK_ListValues());
    }
}
