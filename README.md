# First-Project
# Dream House

This is my first Java project. I have used Hibernate and Spring here.

The main idea of this project is that some construction-company named "Dream House" is building houses and selling flats. On their website there are some functions such as:

  - each user can find houses, which are under construction, see numbers and plans of available flats, pantries and parking places;
  - clients can find their payment schedule and check paid funds;
  - most of all administrator has opportunities. He can add houses, add flats, pantries and parking places to these houses, add clients, their payment schedules, add flats and another objects to the client or take them away if he refused, etc.
  
To begin work with the project, you must log in with the login "admin" and password "admin". Then add a house, add some flats, pantries and parking places. After you can add a client and buy objects for him.

You can log in as a client too and find available functions. Other users don't need log in.

But first you need to change the username and password for your MySQL database. It can be done in file "persistence.xml" which is in "src -> main -> resources -> META-INF".
