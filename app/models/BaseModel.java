package models;

import io.ebean.Model;
import io.ebean.annotation.SoftDelete;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public class BaseModel extends Model {
   @Id
   private int id;
   @SoftDelete
   private boolean deleted;
   @WhenCreated
   private Instant createdAt;
   @WhenModified
   private Instant updatedAt;


   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public boolean isDeleted() {
      return deleted;
   }

   public void setDeleted(boolean deleted) {
      this.deleted = deleted;
   }

   public Instant getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(Instant createdAt) {
      this.createdAt = createdAt;
   }

   public Instant getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(Instant updatedAt) {
      this.updatedAt = updatedAt;
   }
}
