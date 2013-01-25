package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.Friendship;
import it.polimi.SWIMv2.EntityBeans.GenericUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class FriendshipBean
 */
@Stateless
public class FriendshipBean implements FriendshipBeanLocal {

@PersistenceContext(unitName="SWIMv2_PU")
private EntityManager entityManager;
  
    public FriendshipBean() {}

    @Override
public void friendshipRequest(String userEmail1, String userEmail2, boolean direct) {

    Query userQuery1 = entityManager.createQuery("SELECT u FROM GenericUser u WHERE u.email = :email");
    userQuery1.setParameter("email", userEmail1);
   
    Query userQuery2 = entityManager.createQuery("SELECT u FROM GenericUser u WHERE u.email = :email");
    userQuery2.setParameter("email", userEmail2);
   
    
    try{
   
    GenericUser user1 = (GenericUser)userQuery1.getSingleResult();
    GenericUser user2 = (GenericUser)userQuery2.getSingleResult();
   
    Friendship friendship = new Friendship(user1, user2, direct); 
    entityManager.persist(friendship);
   
    }
    catch(Exception e){
    System.out.println(" Richiesta di amicizia non andata a buon fine ");
    }
   
    }

    @Override
public void confirmFriendship(String userEmail1, String userEmail2) {
    Query friendshipQuery = entityManager.createQuery("SELECT f FROM Friendship f WHERE f.friend1.email = :mail2 AND f.friend2.email = :mail1");
     	friendshipQuery.setParameter("mail1", userEmail1);
friendshipQuery.setParameter("mail2", userEmail2);
try{
Friendship friendship = (Friendship)friendshipQuery.getSingleResult();
boolean confirmation = true;
friendship.setConfirmation(confirmation);
entityManager.persist(friendship);
}
catch(Exception e){
System.out.println(" L'amicizia non e' stata confermata ");
}
    }
    
@Override
public List<String> getAllFriends(String userEmail) {
Query userQuery = entityManager.createQuery("SELECT u FROM GenericUser u WHERE u.email = :email");
    userQuery.setParameter("email", userEmail);
    GenericUser user = (GenericUser)userQuery.getSingleResult();
   
    List<GenericUser> friends = new ArrayList<GenericUser>(user.getFriends());
    return getParameters(friends);
}

/**
* No side effects
* 
* @param friends
* @return
*/
private List<String> getParameters(List<GenericUser> friends) {
List<String> result = new ArrayList<String>();
for(GenericUser f: friends){
String tupla = new String(f.getFirstName()+ " " + f.getLastName()+ "  " + f.getEmail());
result.add(tupla);
}
return result;
}

@Override
public List<GenericUser> getTypeABFriends(String userMail, boolean direct) {
Query friendsList1 = entityManager.createQuery("SELECT f.friend2 FROM Friendship f WHERE f.friend1.email = :mail AND f.confirmed = :confirmed AND f.direct = :direct");
friendsList1.setParameter("mail",userMail);
friendsList1.setParameter("direct", direct);
friendsList1.setParameter("confirmed", true);
Query friendsList2 = entityManager.createQuery("SELECT f.friend1 FROM Friendship f WHERE f.friend2.email = :mail AND f.confirmed = :confirmed AND f.direct = :direct");
friendsList2.setParameter("mail", userMail);
friendsList2.setParameter("direct", direct);
friendsList2.setParameter("confirmed", true);
return mergeQueryResults(friendsList1,friendsList2);
}

private List<GenericUser> mergeQueryResults(Query q, Query q2) {
try{
List<GenericUser> l1 = (List<GenericUser>)q.getResultList();
List<GenericUser> l2 = (List<GenericUser>)q2.getResultList();
List<GenericUser> result = new ArrayList<GenericUser>();
return mergeLists(result, l1,l2);
}
catch(Exception e){
return null;
}
}

private List<GenericUser> mergeLists(List<GenericUser> result, List<GenericUser> l1, List<GenericUser> l2) {
result.addAll(l1);
result.addAll(l2);
Set<GenericUser> resultSet = new HashSet<GenericUser>(result);
return new ArrayList<GenericUser>(resultSet);
}


}

