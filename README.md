# demo_restapi
Cari proyekt CRUD əməliyyatların icra ədən sadə restful api-dır ,proyektdə eyni zamanda spring security-də inteqrasiya edilib .

## proyektdə aşağıdakı imkanlar var 

- userlər üzəridə crud əməliyyatları.
- userin sistemə sing in olması.
- userin sistemdə sing up olması.
- api endpointlər üçün customize exception handling olması ,AOP paradiqması istifadə edilib exceptionlar üçün.
- spring security tətbiq edilib,authentication üçün /login path-ə POST metodu ilə username password json formatinda gonderilir (```JwtAuthenticationFilter```),authorization üçün jwt token istifadə edilib ( ```JwtAuthorizationFilter``` ) ,hər girişdə və qeydiyyatdan keçəndə jwt generate edilir,access token-nın ömürü 1 gün,refresh token-nın isə 10 gündür.
- kiçik swagger dokumentasiyası.
- database üçün Spring data,hibernate və mysql istifadə edilib.
- validator olaraq Hibernate Validator istifadə edilib model və dao obyektlər üçün.
- user sing up olarkən default olaraq ```ROLE_USER``` rolun alır,bu rol admin panelindən lazimi user üçün sonradan deyişdirilə bilər.

## api endpointlər
![image](https://user-images.githubusercontent.com/80002048/198831905-c86c97d7-5aa5-41ad-84ea-110e91abd684.png)

## user-in login olması
- userin login olmasi ucun  /login  path-ə  http post ilə json formatinda username ve password gonderilir.
- login əməliyyatı üçün ```JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter``` sinifi istifadə edilib
![image](https://user-images.githubusercontent.com/80002048/198831725-04bd23bb-dd9a-4e62-bf1e-482355f8bb9c.png)

 - sisteme daxil olduqdan sonra jwt token-lər :
 ![image](https://user-images.githubusercontent.com/80002048/198832286-8e8b3b01-2181-4a77-ba19-947fa104770f.png)



## database-dəki table-lar
- burada users table-ında sistem-dəki istifadəçiləri saxlanır,roles table-ında isə user-lərin rolları saxlanılır.

![image](https://user-images.githubusercontent.com/80002048/198362795-0d259e82-d860-4fc4-ab39-be7978ab7fbd.png)

## aşağıdakı api-lara qeyd edilmiş rol-a sahib user-lər müraciət edə bilər.
![image](https://user-images.githubusercontent.com/80002048/198831431-d47635fb-f202-4988-8f9b-c79d3d66f3d0.png)




 

