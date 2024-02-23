# ShopmeCart
admin: https://spring-shopme-admin-25a262e6b73d.herokuapp.com/ 
<br>
user: https://spring-shopme-frontend-a8bd958a75d2.herokuapp.com/

# Getting started
Shopme is a set of commerce modules and tools that allow you to build rich, reliable, and performant commerce applications without reinventing core commerce logic. The modules can be customized and used to build advanced ecommerce stores, marketplaces, or any product that needs foundational commerce primitives.
# Prerequisites
<ul>
  <li>Spring Security</li>
  <li>Spring Security 5</li>
  <li>Thymeleaf</li>
  <li>Spring Data Jpa</li>
  <li>super csv(export csv)</li>
  <li>apache poi(export excel)</li>
  <li>librepdf(export pdf)</li>
  <li>awssdk</li>
  <li>Spring Boot mail</li>
  <li>Spring Boot oauth2-client</li></li>
</ul>

# Overview

<table>
  <tr>
    <td></td>
    <td>Admin</td>
    <td>User</td>
  </tr>
  <tr>
    <td>Dashboard</td>
    <td><img width="944" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/481819ee-01a3-498e-9c37-f480e21914bd"></td>
    <td><img width="940" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/e11d9ec8-5691-4857-b97f-857236a3c657"></td>
  </tr>
  <tr>
    <td>Register</td>
    <td><img width="931" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/5cbf78b8-8164-487f-930d-71d18f182a26"></td>
    <td><img width="950" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/e003fe1f-2cb0-4e89-853e-af8c4132c7ad"></td>
  </tr>
  <tr>
    <td>Login</td>
    <td><img width="960" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/eccf2492-6b89-487d-8887-c0f3031620b0"></td>
    <td><img width="930" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/5a5813bb-2587-47cc-92b1-b6aa4c084cc4"></td>
  </tr>
  <tr>
    <td>User</td>
    <td><img width="960" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/d949484e-1609-4de0-9ea7-5075ce5677a7"></td>
    <td></td>
  </tr>
  <tr>
    <td>Categories</td>
    <td><img width="927" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/30f8b93f-6846-4721-a672-2e928c22cb31"></td>
    <td><img width="935" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/3691429a-f383-4c5e-ad3b-7ef317faa916"></td>
  </tr>
  <tr>
    <td>Brands</td>
    <td><img width="938" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/b5aa6ebd-d38a-46f7-9eef-fda67b939b71"></td>
    <td><img width="800" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/dd66cefb-ae7e-4d38-a9b5-b6bae8c192d8"></td>
  </tr>
  <tr>
    <td>Products</td>
    <td><img width="947" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/d317ce03-9b1c-43b4-8536-60ddabb29e0d"></td>
    <td><img width="943" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/7f7e6691-ced9-46e6-b675-a5a24b0d4c23"></td>
  </tr>
  <tr>
    <td>Product Detail</td>
    <td><img width="854" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/02435ebb-4037-4b74-8c00-4a47f38f4209"></td>
    <td><img width="939" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/1ea72076-c837-4504-92dd-50bfdbc9665d"></td>
  </tr>
  <tr>
    <td>Customer</td>
    <td><img width="960" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/b542c019-945a-4e72-b6d0-da5d9f8942c2"></td>
    <td></td>
  </tr>
  <tr>
    <td>Shipping Rates</td>
    <td><img width="936" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/92e4167c-19e1-4777-bccd-a6bc0c31aeb2"></td>
    <td></td>
  </tr>
  <tr>
    <td>Order</td>
    <td><img width="932" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/cc88ba67-4135-4aa2-9a8d-7a028fe9b664">
    <img width="945" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/c15c3e30-3c70-43ad-a1ae-99f6d9f6533a">
</td>
    <td><img width="939" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/bd3eb331-2fae-4f4f-b8c9-634b7535726a"></td>
  </tr>
  <tr>
    <td>Shopping cart</td>
    <td></td>
    <td><img width="939" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/e43588b9-41ad-4731-ac49-70ed46cc97d3"></td>
  </tr>
  <tr>
    <td>Checkout</td>
    <td></td>
    <td><img width="922" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/e43588b9-41ad-4731-ac49-70ed46cc97d3">
    <img width="947" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/a2ae1057-faa7-42ca-bfcc-9caf2932306d">
</td>
  </tr>
  <tr>
    <td>Sales Report</td>
    <td><img width="940" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/e2740e9b-e5bc-4c22-8d18-df28d361f102"></td>
    <td></td>
  </tr>
  <tr>
    <td>Article</td>
    <td><img width="935" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/45d7abde-4694-428a-97ad-781980196227"></td>
    <td><img width="719" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/4c4f959a-c00c-4d72-b051-e35840d67b9b"></td>
  </tr>
  <tr>
    <td>Menus</td>
    <td><img width="937" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/80fb4ac9-3fb2-4dcf-b9d8-90f79b8e8ff4"></td>
    <td></td>
  </tr>
  <tr>
    <td>Setting</td>
    <td><img width="940" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/8501f73b-4842-4033-94e0-b9dd2173ce5d"></td>
    <td></td>
  </tr>
  <tr>
    <td>Section</td>
    <td><img width="948" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/895c10a8-9766-413c-a635-da122c168b7d"></td>
    <td></td>
  </tr>
  <tr>
    <td>Question</td>
    <td><img width="924" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/d7cad0f7-73ef-4bb1-90c9-571a5faee869"></td>
    <td><img width="940" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/42368d3c-03d4-4ea2-9d9c-73088cad3438">
    <img width="461" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/ad177a0e-430c-4f74-b2b7-dbbc433972bb">
</td>
  </tr>
  <tr>
    <td>Review</td>
    <td><img width="938" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/94c58c4f-cdba-44a8-aa79-03b9300dc74d"></td>
    <td><img width="872" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/4a745030-2a0a-4a2f-8bdd-31c4ff06238c">
    <img width="520" alt="image" src="https://github.com/Minhvu87/ShopmeCart/assets/44714241/de06d360-d35e-4807-9671-7173b4b82918">

    </td>
  </tr>
    
  
</table>




