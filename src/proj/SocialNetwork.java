package src.proj;

import java.util.*;
import java.util.stream.Collectors;

public class SocialNetwork {
    private Graph<User> network;

    public SocialNetwork(boolean directed)
    {
        network = new Graph<>(directed);
    }

    //Creates a user on the network
    public void addUser(User user)
    {
        network.addNode(user);
    }

    //Connects users
    public void connectUsers(User u1, User u2, int strength)
    {
        network.addEdge(u1, u2, strength);
    }

    //Shows the connections that a user has
    public void showConnections(User user)
    {
        System.out.println(user.getName() + "'s Connections:");

        for(User other: network.getNodeIndexMap().keySet())
        {
             if(!user.equals(other))
             {
                int weight = network.edgeWeight(user, other);

                if (weight > 0)
                {
                    System.out.printf("- %s (strength: %d) %n", other.getName(), weight);
                }
             }
        }
    }

    //Prints out the network of friends
    public void showNetwork()
    {
        System.out.println("\nSocial Network Graph:");
        network.displayMAtrix();
    }

    //Check to see if user exists
    public boolean userExist(User user)
    {
        List<User> listOfUSers = network.getNodes();

        for (int i = 0; i < listOfUSers.size(); i++)
        {
            if(user.equals(listOfUSers.get(i)))
                return true;
        }

        return false;
    }

    //Counts the amount of friends of an user
    public int userFriendcoutner(User user)
    {
        int i = network.edgeCounter(user);

        return i;
    }

    //Checks to see if two users are friends
    public boolean friendsChecker(User u1, User u2)
    {
        return network.edgeExist(u1, u2);
        
    }

    //Suggest friends for an user 
    public  List<User> suggestFriends(User user)
    {
        Set<User> areFriends = new HashSet<>();
        List<User> users = new ArrayList<>(network.getNodeIndexMap().keySet());

        for (User u: users)
        {
            if(!user.equals(u) && network.edgeWeight(user, u) > 0)
            {
                areFriends.add(u);
            }
        }

        Map<User, Integer> suggestionCounts = new HashMap<>();

        for(User friend : areFriends)
        {
            for(User mutualFriend : users)
            {
                if(!mutualFriend.equals(user) && !areFriends.contains(mutualFriend) && network.edgeWeight(friend, mutualFriend) > 0 
                && network.edgeWeight(user, mutualFriend) == 0)
                {
                    suggestionCounts.put(mutualFriend,suggestionCounts.getOrDefault(mutualFriend, 0)+1);

                }
            }
        }

        List<Map.Entry<User, Integer>> sorted = new ArrayList<>(suggestionCounts.entrySet());
        sorted .sort((a,b) -> Integer.compare(b.getValue(), a.getValue()));

        List<User> sortedSuggestions = suggestionCounts.entrySet() .stream().sorted((a, b) -> Integer.compare(b.getValue(), a.getValue())) .map(Map.Entry::getKey)
        .collect(Collectors.toList());
 
       //System.out.println("\nSuggested friends for " + user.getName() + ":");
       // if(sorted.isEmpty())
       // {
       //     System.out.println("- No suggestions found.");
       // 
       // }
       // else 
       // {
       //     for (Map.Entry<User, Integer> entry : sorted) 
       //      {
       //         System.out.printf("- %s (mutual friends: %d)%n", entry.getKey().getName(), entry.getValue());
       //      }
       // }

        return sortedSuggestions;
    }
}
