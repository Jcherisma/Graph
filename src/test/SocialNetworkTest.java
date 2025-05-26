package src.test;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import src.proj.*;

public class SocialNetworkTest {
    
private SocialNetwork socialNetwork; 
private User tyler, vini, ronaldo, jeff, martha, jess, john;

@Before
public void setup() {
    socialNetwork = new SocialNetwork(false); 

    tyler = new User("Tyler", "Just a chill guy", 17 );
    vini = new User("Vini", "Real Madrid Playe", 24 );
    ronaldo = new User("Ronaldo", "Just a Goat", 39 );
    jeff = new User("Jeff", "I'm just here", 25 );
    martha = new User("Martha", "That girl", 22 );
    jess = new User("Jess", "Hi", 15 );
    john = new User("John", ":)", 16 );

    //adding users to the network
    socialNetwork.addUser(tyler);
    socialNetwork.addUser(vini);
    socialNetwork.addUser(ronaldo);
    socialNetwork.addUser(jeff);
    socialNetwork.addUser(martha);
    socialNetwork.addUser(jess);

    //creating connections between users
    socialNetwork.connectUsers(jess, jeff, 1);
    socialNetwork.connectUsers(jeff, ronaldo, 1);
    socialNetwork.connectUsers(ronaldo, vini, 1);
    socialNetwork.connectUsers(tyler, jess, 1);

}

//Test to see if users were added to the network properly
@Test
public void test1()
{
  boolean expected =  socialNetwork.userExist(jeff);

  assertTrue(expected);            
}

@Test
public void test2()
{
  boolean expected =  socialNetwork.userExist(jeff);

  assertTrue(expected);            
}

@Test
public void test3()
{
  boolean expected =  socialNetwork.userExist(john);

  assertFalse(expected);           
}

//Test counting the amount of a friend does a user have
@Test
public void test4()
{
  int expected = 2;      
  int actual =  socialNetwork.userFriendcoutner(jeff);

  assertEquals(expected, actual);            
}

@Test
public void test5()
{
  int expected = 1;      
  int actual =  socialNetwork.userFriendcoutner(tyler);

  assertEquals(expected, actual);            
}

@Test
public void test6()
{
  int expected = 2;      
  int actual =  socialNetwork.userFriendcoutner(ronaldo);

  assertEquals(expected, actual);            
}

@Test
public void test7()
{
  int expected = 0;      
  int actual =  socialNetwork.userFriendcoutner(martha);

  assertEquals(expected, actual);            
}

@Test
public void test8()
{
  int expected = 1;      
  int actual =  socialNetwork.userFriendcoutner(vini);

  assertEquals(expected, actual);            
}

@Test
public void test9()
{
  int expected = 2;      
  int actual =  socialNetwork.userFriendcoutner(jess);

  assertEquals(expected, actual);            
}

//Tests to see if users are friends or not
@Test
public void test10()
{
   boolean expected = socialNetwork.friendsChecker(jess, jeff); 
   
   assertTrue(expected);            
}

@Test
public void test11()
{
   boolean expected = socialNetwork.friendsChecker(ronaldo, jeff); 
   
   assertTrue(expected);            
}

@Test
public void test12()
{
   boolean expected = socialNetwork.friendsChecker(ronaldo, vini); 
   
   assertTrue(expected);            
}

@Test
public void test13()
{
   boolean expected = socialNetwork.friendsChecker(jess, tyler); 
   
   assertTrue(expected);            
}

@Test
public void test14()
{
   boolean expected = socialNetwork.friendsChecker(vini, tyler); 
   
   assertFalse(expected);            

}

@Test
public void test15()
{
   boolean expected = socialNetwork.friendsChecker(jess, ronaldo); 
   
   assertFalse(expected);            

}

//Testing to see if users can get suggested friends
@Test
public void test16()
{
         List<User> suggestions = socialNetwork.suggestFriends(jeff);
        assertTrue("Tyler should be suggested to Jeff via Jess",suggestions.contains(tyler));
        assertFalse("Jess is already a friend of Jeff and should not be suggested",suggestions.contains(jess));
        assertFalse("Ronaldo is already a friend of Jeff and should not be suggested", suggestions.contains(ronaldo));
}

@Test
public void test17()
{
         List<User> suggestions = socialNetwork.suggestFriends(jess);
        assertTrue("Ronaldo should be suggested to Jess via Jeff",suggestions.contains(ronaldo));
        assertFalse("Jeff is already a friend of Jess and should not be suggested",suggestions.contains(jeff));
        assertFalse("Ronaldo and John have no Friends in common", suggestions.contains(john));

}

@Test
public void test18()
{
        List<User> suggestions = socialNetwork.suggestFriends(ronaldo);
        assertTrue("Jess should be suggested to Ronaldo via Jeff",suggestions.contains(jess));
        assertFalse("Jeff is already a friend of Ronaldo and should not be suggested",suggestions.contains(jeff));
        assertFalse("Ronaldo and John have no Friends in common", suggestions.contains(john));

}

@Test
public void test19()
{
        List<User> suggestions = socialNetwork.suggestFriends(vini);
        assertTrue("Jeff should be suggested to Vini via Ronaldo",suggestions.contains(jeff));
        assertFalse("Vini and Martha have no Friends in common",suggestions.contains(martha));
        assertFalse("Vini and John have no Friends in common", suggestions.contains(john));

}

@Test
public void test20()
{
        List<User> suggestions = socialNetwork.suggestFriends(martha);
        assertFalse("Martha and Jeff have no Friends in common",suggestions.contains(jeff));
        assertFalse("Martha and Jess have no Friends in common",suggestions.contains(jess));
        assertFalse("Martha and John have no Friends in common", suggestions.contains(john));

}

}