import sys
acc_table = {}
with open(sys.argv[1],"r+",encoding="utf-8") as accounts:
    for i in accounts:
        i = i.strip(" \n")
        persons,friends = i.split(":")
        friends = list(friends.split(" "))
        acc_table[persons] = friends        
    accounts.close()

com_list = []
with open(sys.argv[2],"r+",encoding="utf-8") as commands:
    for j in commands:
      com_list.append(j.split())
    commands.close()

with open("output.txt","w+",encoding="utf-8") as outputs:
    for a in com_list:
        if len(a) < 2:
            outputs.write("ERROR: Not enough input.(expected at least 2\n")
        elif len(a) == 2:
            if a[0] == "ANU":
                if a[1] not in acc_table.keys():
                    acc_table[a[1]] = []
                    outputs.write(f"User '{a[1]}' has been added to the social media network succesfully.\n")
                else:
                    outputs.write(f"ERROR:Wrong input type for 'ANU'!-- This user already exists!--\n")
            elif a[0] == "DEU":                
                if a[1] not in acc_table.keys():
                    outputs.write(f"ERROR:Wrong input type for 'DEU'!-- There is no user named '{a[1]}'.\n")
                else:   
                    if a[1] in acc_table.keys():
                        acc_table.pop(a[1])
                        for lists in acc_table.values():
                            for deleted in list(lists):
                                if deleted == a[1]:
                                    lists.remove(deleted)                                                                                 
                    outputs.write(f"User '{a[1]}' and his/her all relations have been deleted succesfully.\n")
            elif a[0] == "CF":
                if a[1] not in acc_table.keys():
                    outputs.write(f"ERROR:Wrong input type for 'CF'!-- There is no user named '{a[1]}'.\n")
                else:
                    outputs.write(f"User '{a[1]}' has {len(acc_table[a[1]])} friends.\n")
        elif len(a) == 3:
            if a[0] == "ANF":
                if a[1] not in acc_table.keys() and a[2] not in acc_table.keys():
                    outputs.write(f"ERROR:Wrong input type for 'ANF'!-- There are no user named '{a[1]}' and '{a[2]}'.\n")
                elif a[1] not in acc_table.keys():
                    outputs.write(f"ERROR:Wrong input type for 'ANF'!-- There is no user named '{a[1]}'.\n")
                elif a[2] not in acc_table.keys():
                    outputs.write(f"ERROR:Wrong input type for 'ANF'!-- There is no user named '{a[2]}'.\n")                
                elif a[1] in acc_table[a[2]]:
                    outputs.write(f"ERROR:Wrong input type for 'ANF'!--'{a[1]}' and '{a[2]}' already friends!\n")
                else:
                    acc_table[a[1]].append(a[2])
                    acc_table[a[2]].append(a[1])
                    outputs.write(f"Relation between '{a[1]}' and '{a[2]}' has been added succesfully.\n")
            elif a[0] == "DEF":
                if a[1] and a[2] not in acc_table.keys():
                    outputs.write(f"ERROR:Wrong input type for 'DEF'!-- There are no user named '{a[1]}' and '{a[2]}'.\n")
                elif a[1] not in acc_table.keys():
                    outputs.write(f"ERROR:Wrong input type for 'DEF'!-- There is no user named '{a[1]}'.\n")
                elif a[2] not in acc_table.keys():
                    outputs.write(f"ERROR:Wrong input type for 'DEF'!-- There is no user named '{a[2]}'.\n")
                elif a[1] not in acc_table[a[2]]:
                    outputs.write(f"ERROR:Wrong input type for 'DEF'!--'{a[1]}' and '{a[2]}' are not friends.\n")
                else:
                    while a[2] in acc_table[a[1]]:
                        acc_table[a[1]].remove(a[2])
                    while a[1] in acc_table[a[2]]:
                        acc_table[a[2]].remove(a[1])
                    outputs.write(f"Relation between '{a[1]}' and '{a[2]}' has been deleted succesfully.\n")
            elif a[0] == "FPF":
                if a[1] not in acc_table.keys():
                   outputs.write(f"ERROR:Wrong input type for 'FPF'!-- There is no user named '{a[1]}'.\n")
                elif len(acc_table[a[1]]) < 2:
                                    outputs.write(f"ERROR: User '{a[1]}' has less friends than maximum distance!\n")
                else:                    
                    max_d = int(a[2])
                    if max_d >= 1  and max_d <=3:
                        if max_d == 1:
                            outputs.write(f"User '{a[1]}' has {len(acc_table[a[1]])} possible friends when maximum distance is 1:\n")
                            outputs.write(f"These possible friends: {{{str(sorted(set(acc_table[a[1]])))[1:-1]}}}\n")
                        elif max_d == 2:                           
                            list1 = []
                            list2 = []
                            for key in acc_table[a[1]]:  
                             list1.append(key)
                            old = len(list1)                          
                            z=0
                            while z < old:  
                              z+=1
                              for keys in acc_table.keys():                                                   
                               if keys in acc_table[list1[z-1]]:                               
                                list1.append(keys)
                            for characters in list1:
                             if characters not in list2:
                                 list2.append(characters)
                            if a[1] in list2:
                              list2.remove(a[1])                                                      
                            outputs.write(f"User '{a[1]}' has {len(list2)} possible friends when maximum distance is 2:\n")
                            outputs.write(f"These possible friends: {{{str(sorted(set(list2)))[1:-1]}}}\n")
                        else:
                            list3 = []
                            list4 = []
                            list5= []
                            for key in acc_table[a[1]]:  
                             list3.append(key)
                             old = len(list3)                          
                             z=0
                            while z < old:  
                              z+=1
                              for keys in acc_table.keys():                                                   
                               if keys in acc_table[list3[z-1]]:                               
                                list3.append(keys)                            
                            for characters in list3:
                             if characters not in list4:
                                 list4.append(characters)
                            old2 = len(list4)
                            z = 0
                            while z < old2:
                              z+=1
                              for q in acc_table.keys():
                               if q in acc_table[list4[z-1]]:
                                   list4.append(q)
                            for character in list4:
                             if character not in list5:
                                 list5.append(character)
                            if a[1] in list5:
                               list5.remove(a[1])                                                        
                            outputs.write(f"User '{a[1]}' has {len(list5)} possible friends when maximum distance is 3:\n")
                            outputs.write(f"These possible friends: {{{str(sorted(set(list5)))[1:-1]}}} \n")                        
            elif a[0] == "SF":               
                if a[1] not in acc_table.keys():
                   outputs.write(f"ERROR:Wrong input type for 'SF'!-- There is no user named '{a[1]}'.\n")
                else:                                                                        
                    mut_d = int(a[2])
                    if mut_d >=2  and mut_d <=3:                         
                        if mut_d == 2:
                            list_a1_2 = acc_table.get(a[1])
                            suggested = []
                            outputs.write(f"Suggestion list for '{a[1]}' (when MD is 2):\n")
                            for d,e in acc_table.items():  
                                if d in acc_table[a[1]]:
                                    pass                              
                                elif d!= a[1] and len(list(set(list_a1_2).intersection(e))) == 2:
                                   suggested.append(d)
                                   outputs.write(f"'{a[1]}' has 2 mutual friends with '{d}'.\n")
                            for h,ı in acc_table.items():
                                if h in acc_table[a[1]]:
                                    pass
                                elif h!= a[1] and len(list(set(list_a1_2).intersection(ı))) == 3:
                                   suggested.append(h)
                                   outputs.write(f"'{a[1]}' has 3 mutual friends with '{h}'.\n")                                                                                                                    
                            outputs.write(f"The suggested friends for '{a[1]}':{str(suggested)[1:-1]}\n") 
                        elif mut_d == 3:
                            suggested2 = []
                            list_a1_3 = acc_table.get(a[1])
                            outputs.write(f"Suggestion list for '{a[1]}' (when MD is 3):\n")
                            for f,g in acc_table.items():
                               if f in acc_table[a[1]]:
                                    pass
                               elif f != a[1] and len(list(set(list_a1_3).intersection(g)))  == 3 :   
                                    suggested2.append(f)                                
                                    outputs.write(f"'{a[1]}' has 3 mutual friends with '{f}'.\n")
                            outputs.write(f"The suggested friends for '{a[1]}':{str(suggested2)[1:-1]}\n") 
                    else:
                        outputs.write(f"ERROR:Mutuality degree can only be in [2,3] interval.\n")
        else:
            outputs.write(f"ERROR:Command index out of range!")