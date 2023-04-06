print("<-----RULES----->""\n""1. BRUSH DOWN""\n""2. BRUSH UP""\n""3. VEHICLE ROTATES RIGHT""\n""4. VEHICLE ROTATES LEFT"
"\n""5. MOVE UP TO X""\n""6. JUMP""\n""7. REVERSE DIRECTION""\n""8. VIEW THE MATRIX""\n""0. EXIT""\n""Please enter the commands with a plus sign (+) between them.")
List =input("").split("+")

N = int(List[0])
del List[0]

board = [[" " for a in range(N)]for b in range(N)]
car_x = 0
car_y = 0
dir = ["right","left","upward","downward"]
brush_down = 0

for c in List:
      
    if c == "1":
        brush_down = 1
        board[car_y][car_x] = "*"
    elif c == "2":
        brush_down = 0
    elif c == "3":
        if dir[0] == "right":
            dir[0] ="downward"
        elif dir[0] == "downward":
            dir[0] ="left"
        elif dir[0] == "left":
            dir[0] ="upward"
        elif dir[0] == "upward":
            dir[0] = "right"
    
    elif c == "4":
        if dir[0] =="right":
            dir[0] = "upward"
        elif dir[0] =="upward":
            dir[0] = "left"
        elif dir[0] == "left":
            dir[0] = "downward"
        elif dir[0] == "downward":
            dir[0] = "right"
         
    elif c[0] == "5":
       if dir[0] == "right":
        for d in range(int(c[2])):
            if car_x < N-1:
             car_x +=1             
            elif car_x ==N-1:
                car_x= (car_x-N)+1
            if brush_down == 1:
             board[car_y][car_x] = "*"           
                                     
       elif dir[0] == "left":
        for e in range(int(c[2])):
           if car_x > 0:
            car_x -=1
           elif car_x == 0:
               car_x = (car_x+N)-1
           if brush_down == 1:
             board[car_y][car_x] = "*"
                                     
       elif dir[0] == "upward":
        for f in range(int(c[2])):
           if car_y >0:
               car_y-=1
           elif car_y ==0:
               car_y=(car_y+N)-1            
           if brush_down == 1:
             board[car_y][car_x] = "*" 
            
       elif dir[0] == "downward":
         for g in range(int(c[2])):
          if car_y < N-1:
           car_y +=1  
          elif car_y == N-1:
            car_y= (car_y-N)+1
          if brush_down == 1:
            board[car_y][car_x] = "*"

    elif c == "6":
        brush_down = 0
        if dir[0] =="right":
            if car_x == N-3:
                car_x =0
            elif car_x == N-2:
                car_x =1
            elif car_x == N-1:
                car_x =2
            else:               
             car_x += 3
        elif dir[0] == "left":
            if car_x ==0:
                car_x =N-3
            elif car_x ==1:
                car_x =N-2
            elif car_x ==2:
                car_x =N-1
            else:
                car_x+=3
        elif dir[0] == "downward":
            if car_y ==N-3:
                car_y =0
            elif car_y==N-2:
                car_y =1
            elif car_y ==N-1:
                car_y =2
            else:
                car_y+=3
        elif dir[0] == "upward":
            if car_y ==0:
                car_y=N-3
            elif car_y==1:
                car_y = N-2
            elif car_y == 2:
                car_y = N-1
    elif c == "7":
        if dir[0] =="right":
            dir[0] ="left"
        elif dir[0] =="left":
            dir[0] ="right"
        elif dir[0] =="upward":
            dir[0] = "downward"
        elif dir[0] =="downward":
            dir[0] ="upward"
    elif c =="8":
        i = "+" *N
        print((N+2) * "+")
        for i in range(N):
          print("+"+"".join(board[i])+"+")
        print((N+2) * "+")

    elif c == "0":
        break
                 




         
    


        
            
