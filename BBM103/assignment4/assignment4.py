import sys
matrix = []
with open(sys.argv[1],"r",encoding="utf-8") as inputs:
    for i in inputs:
        i = i.strip(" \n")
        matrix.append(i.split(" "))
    inputs.close()

for i in matrix:
    print(" ".join(i))
print("\nYour score is: 0\n")

try:
 list2 =[]
 def control(a,b): #Firsth if-else block controls the input.Function must take input while there is at least one mutuality between indexes.
  try:
    if matrix[a][b]!= " " and matrix[a][b]== "X" or matrix[a][b]!= " " and b+1<len(matrix[0]) and matrix[a][b] == matrix[a][b+1] or matrix[a][b]!= " " and b-1>=0 and matrix[a][b] == matrix[a][b-1] or matrix[a][b]!= " " and a+1 <len(matrix) and matrix[a][b] == matrix[a+1][b] or matrix[a][b]!= " " and a-1>=0 and matrix[a][b] == matrix[a-1][b]:
        list1 = [matrix[a][b]]
    else:
        list1 = []
        if b+1 < len(matrix[0]):
            return control(a,b+1)
        elif b+1 >= len(matrix[0]) and a+1<len(matrix):
            return(control(a+1,0))
        if b+1 == len(matrix[0]) and a+1 == len(matrix):
            pass    
    while len(list1) == 1:
        list1 = []
        choose = input("Please enter a row and column number: ").split(" ")
        chosen = [int(choose[i]) for i in range(len(choose))]
        row,col = chosen[0],chosen[1]
        first_row = chosen[0]
        first_col = chosen[1]
        color = matrix[first_row][first_col]
        deleted = " "
        f = 0
        
        if matrix[chosen[0]][chosen[1]] == "X": # If player choose 'X', bomb will explode.
            def recursion1(a,b):
                        for k in range(a,len(matrix)):
                            list2.append(matrix[k][b])
                            matrix[k][b] = " "
                            if k+1 < len(matrix) and matrix[k+1][b] == "X":
                                recursion1(a=k+1,b=b)
                        for k in range(a,-1,-1):
                            list2.append(matrix[k][b])
                            matrix[k][b] = " "
                            if k-1 >=0 and matrix[k-1][b] == "X":
                                recursion1(a=k-1,b=b)
                        for k in range(b,len(matrix[0])):
                            list2.append(matrix[a][k])
                            matrix[a][k] = " "
                            if k+1 < len(matrix[0]) and matrix[a][k+1] == "X":
                                recursion1(a=a,b=k+1)
                        for k in range(b,-1,-1):
                            list2.append(matrix[a][k])
                            matrix[a][k] = " "
                            if k-1 >= 0 and matrix[a][k-1] == "X":
                                recursion1(a=a,b=k-1)                          
            recursion1(a=chosen[0],b=chosen[1])
        else:                                         # This function searches for the mutuality between chosen index and indexes next to it.
            def recursion(a,b):
                    if b+1< len(matrix[0]) and matrix[a][b+1] == color:
                        list2.append(matrix[a][b]) 
                        list2.append(matrix[a][b+1]) 
                        matrix[a][b] = " "
                        matrix[a][b+1] = " "
                        recursion(a=a,b=b+1)
                    if a+1 < len(matrix) and matrix[a+1][b] == color:
                        list2.append(matrix[a][b])
                        list2.append(matrix[a+1][b]) 
                        matrix[a][b] = " "
                        matrix[a+1][b] = " "
                        recursion(a=a+1,b=b)
                    if a-1 >= 0 and matrix[a-1][b] == color:
                        list2.append(matrix[a][b]) 
                        list2.append(matrix[a-1][b]) 
                        matrix[a][b] = " "
                        matrix[a-1][b] = " "
                        recursion(a=a-1,b=b)
                    if b-1>=0 and matrix[a][b-1] == color:
                        list2.append(matrix[a][b]) 
                        list2.append(matrix[a][b-1]) 
                        matrix[a][b] = " "
                        matrix[a][b-1] = " "
                        recursion(a=a,b=b-1)
                    else:
                        if chosen[0]+1 <len(matrix) and matrix[chosen[0]+1][chosen[1]] == color or chosen[0]-1 >= 0 and matrix[chosen[0]-1][chosen[1]] == color or chosen[1]+1 <len(matrix[0]) and matrix[chosen[0]][chosen[1]+1] == color or chosen[1]-1 >=0 and matrix[chosen[0]][chosen[1]-1] == color:          
                            recursion(a= chosen[0],b = chosen[1])
                        else:
                            pass
            recursion(a=chosen[0],b=chosen[1])
                
        f,d,e= 0,0,0
        deleted_list = []                  
        while f+1 <= len(matrix): # Dropping ballons which are at the top when the bottom ballon has been deleted.
                    if f == len(matrix)-1:
                        if d+1 < len(matrix[0]):  
                                d+=1 
                                f =0   
                                        
                    while d<= len(matrix[0]) and f+1 < len(matrix) and matrix[f][d] != deleted and matrix[f+1][d] == deleted: 
                        f+=1
                        if f -1>=0:           
                            matrix[f][d] = matrix[f-1][d]
                            matrix[f-1][d] = deleted                           
                            f=0                                                   
                    else:
                        f+=1
                    for j in matrix: # Deleting rows.
                        if j == list(len(matrix[0])*" ") :
                            matrix.pop(matrix.index(j))
                            f=-1
                            

                    for j in range(len(matrix)): #Deleting columns.
                        if matrix[j][e] == " ":
                            deleted_list.append(matrix[j][e])
                    if len(deleted_list) == len(matrix):
                        for i in range(len(matrix)):
                            matrix[i].pop(e)
                            d=-1
                    if e+1 < len(matrix[0]):
                        e+=1
                        deleted_list = []
                    else:
                        e = 0
                        deleted_list = []
        
        else:
                    f = 0
        print("")            
        for i in matrix:           #Visualizing matrix.
            print(" ".join(i))
        
        total = 0 # Sum of the deleted balls's values.
        for word in list2:
            if word == "B":
                total+=9
            elif word == "G":
                total+=8
            elif word == "W":
                total+=7
            elif word == "Y":
                total+=6
            elif word == "R":
                total+=5
            elif word == "P":
                total+=4
            elif word == "O":
                total+=3
            elif word == "D":
                total+=2
            elif word == "F":
                total+=1
            elif word == "X":
                total+=0
        print(f"\nYour score is: {total}\n")#Printing the score.
        control(a=0,b=0)
  except:
    if len(matrix[0]) !=0:
        print("\nPlease enter a valid size!\n")
        control(a=0,b=0)
    else:
        for i in matrix:
            print(" ".join(i))
 control(a=0,b=0)   #Calling 'control' function.
finally:
    print("Game over!\n")       