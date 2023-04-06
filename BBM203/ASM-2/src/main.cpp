/*                                 ---INCOMPLETED FUNCTIONS INFORMATION---
   I couldn't make 'relocate_flats_to_apartments' function. But I won't delete the codes, I just make them as comment so that you can review my code.
The problem is, when I change the next or prev pointers of temp->flat, the linked lists that our chosen flats belong are also changes and I couldn't help it.
For example when commands says relocate [17] to apartment X,X changes and the list of 17 in is also changes.
   And also, my free apartment function doesn't free the flats.    */

#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <stdlib.h>
#include <bits/stdc++.h>

using namespace std;


struct Flat  // Flat structure. Indicates the properties of flat.
{
    int id;
    int initial_bandwidth;
    bool is_empty;
    struct Flat *next;
    struct Flat *prev;
};
Flat *head_fl = NULL;

struct Apartment  // Apartment structure. Indicates the properties of apartment and pointer of Flat.
{
    string name;
    int max_bandwidth;
    int instant_bandwidth;
    struct Apartment *next;
    Flat *flat;
};
Apartment *head_ap = NULL; // Creating the head node of circular linked list.

void add_apartment(string apartment_name, string position, int max_bandwith) // Insertion function for adding appartment. This function use 'circular linked list' data structure.
{
    struct Apartment *new_apartment = new Apartment;
    new_apartment->name = apartment_name;
    new_apartment->max_bandwidth = max_bandwith;
    new_apartment->instant_bandwidth = max_bandwith;

    struct Apartment *temp = head_ap;
    struct Apartment *last = head_ap;
    if (position == "head")     // Makes operations if command contains 'head' word.
    {

        if (head_ap == NULL)
        {
            head_ap = new_apartment;
            new_apartment->next = head_ap;
            new_apartment->flat = NULL;
            return;
        }
    }
    else if (position.substr(0, 5) == "after")  // Makes operations if command contains 'after' word.
    {
        do
        {
            if (position.substr(position.find("_") + 1) == temp->name)
            {
                new_apartment->next = temp->next;
                temp->next = new_apartment;
                new_apartment->flat = NULL;
            }
            temp = temp->next;
        } while (temp != head_ap);
    }
    else if (position.substr(0, 6) == "before")  // Makes operations if command contains 'before' word.
    {
        struct Apartment *current = head_ap;
        struct Apartment *prev = head_ap;
        do
        {

            if (position.substr(position.find("_") + 1) == current->name)
            {
                if (current == head_ap)
                {
                    new_apartment->flat = NULL;
                    new_apartment->next = current;
                    current->next = new_apartment;
                    head_ap = new_apartment;
                }
                else
                {
                    new_apartment->flat = NULL;
                    new_apartment->next = current;
                    prev->next = new_apartment;
                }
            }
            if (current != head_ap)
            {
                prev = prev->next;
            }

            current = current->next;

        } while (current != head_ap);
    }
}

void add_flat(string apartment_name, int index, int initial_bandwith, int flat_id) // Doubly linked list insertion.
{
    struct Flat *new_flat = new Flat;
    struct Apartment *temp = head_ap;
    new_flat->id = flat_id;
    new_flat->initial_bandwidth = initial_bandwith;
    new_flat->is_empty = 0;
    int count_index = 0;
    int constant_index = index;

    do
    {

        if (apartment_name == temp->name)
        {
            if (temp->instant_bandwidth < new_flat->initial_bandwidth)
            {
                new_flat->initial_bandwidth = temp->instant_bandwidth;
            }

            if (temp->flat == NULL && index == 0)
            {
                temp->flat = new_flat;
                temp->instant_bandwidth -= new_flat->initial_bandwidth;
                new_flat->next = NULL;
                new_flat->prev = NULL;
                return;
            }
            else
            {

                do
                {
                    if (temp->flat->next != NULL)
                    {
                        temp->flat = temp->flat->next;
                        count_index++;
                    }
                    index--;

                } while (index > 1);

                if ((temp->flat->next == NULL && constant_index > count_index) || (temp->flat->prev == NULL && constant_index > count_index))
                {

                    new_flat->next = temp->flat->next;
                    temp->flat->next = new_flat;
                    new_flat->prev = temp->flat;
                    temp->instant_bandwidth -= new_flat->initial_bandwidth;
                    while (temp->flat->prev != NULL)
                    {
                        temp->flat = temp->flat->prev;
                    }
                    return;
                }
                else if ((temp->flat->next == NULL && constant_index == count_index) || (temp->flat->prev == NULL && constant_index == count_index))
                {
                    if (temp->flat->prev != NULL)
                    {
                        new_flat->next = temp->flat;
                        temp->flat->prev->next = new_flat;
                        new_flat->prev = temp->flat->prev;
                        temp->flat->prev = new_flat;
                        temp->instant_bandwidth -= new_flat->initial_bandwidth;
                    }
                    else
                    {
                        new_flat->next = temp->flat;
                        new_flat->prev = temp->flat->prev;
                        temp->flat->prev = new_flat;
                        temp->instant_bandwidth -= new_flat->initial_bandwidth;
                    }

                    while (temp->flat->prev != NULL)
                    {
                        temp->flat = temp->flat->prev;
                    }
                    return;
                }
                else if (temp->flat->next != NULL && constant_index > count_index)
                {
                    new_flat->next = temp->flat->next;
                    temp->flat->next->prev = new_flat;
                    temp->flat->next = new_flat;
                    new_flat->prev = temp->flat;
                    temp->instant_bandwidth -= new_flat->initial_bandwidth;
                    while (temp->flat->prev != NULL)
                    {
                        temp->flat = temp->flat->prev;
                    }
                    return;
                }
                else if (temp->flat->next != NULL && constant_index == count_index)
                {

                    new_flat->next = temp->flat;
                    temp->flat->prev->next = new_flat;
                    new_flat->prev = temp->flat->prev;
                    temp->flat->prev = new_flat;
                    temp->instant_bandwidth -= new_flat->initial_bandwidth;
                    while (temp->flat->prev != NULL)
                    {
                        temp->flat = temp->flat->prev;
                    }
                    return;
                }
            }
        }
        temp = temp->next;
    } while (temp != head_ap);
}

Flat *remove_apartment(string apt_name) // Removes given apartment. But couldnt'free the flats. 
{
    struct Apartment *temp = head_ap;
    struct Apartment *temp2 = head_ap->next; // this one will be deleted
    struct Apartment *deleted = NULL;
    struct Flat *del_flat = NULL;
    do
    {

        if (temp->next->name == apt_name)
        {

            if (temp->next->name == head_ap->name)
            {
                temp->next = temp2->next;
                head_ap = temp->next;
                deleted = temp2;
                temp2->next = NULL;
                free(temp2);
                return deleted->flat;
            }
            else
            {

                temp->next = temp2->next;
                deleted = temp2;
                temp2->next = NULL;
                free(temp2);

                return deleted->flat;
            }
        }
        temp = temp->next;
        temp2 = temp2->next;
    } while (temp->next != head_ap);
}

void make_flat_empty(string apt_name, int flat_id) // Makes the bandwith 0.
{
    struct Apartment *temp = head_ap;
    do
    {
        if (temp->name == apt_name)
        {
            while (temp->flat->id != flat_id)
            {
                temp->flat = temp->flat->next;
            }
            temp->flat->is_empty = 1;
            temp->flat->initial_bandwidth = 0;
            while (temp->flat->prev != NULL)
            {
                temp->flat = temp->flat->prev;
            }
            return;
        }
        temp = temp->next;
    } while (temp->next != head_ap);
}

int find_sum_of_max_bandwith(ofstream& output) // Sum all bandwiths of apartments and returns it.
{
    struct Apartment *temp = head_ap;
    int total_bandwith = 0;

    if (head_ap == NULL)
    {
        return 0;
    }
    else
    {
        do
        {
            total_bandwith += temp->max_bandwidth;
            temp = temp->next;
        } while (temp->next != head_ap);
        total_bandwith += temp->max_bandwidth;
    }
    output << "sum of bandwidth: " << total_bandwith << "\n"
           << endl;
    return total_bandwith;
}
void merge_two_apartments(string apt1, string apt2) // Merging two apartments and then remove the second one.
{
    struct Apartment *temp = head_ap;  // apt1
    struct Apartment *temp2 = head_ap; // apt2
    while (temp->name != apt1)
    {
        temp = temp->next;
    }
    while (temp2->name != apt2)
    {
        temp2 = temp2->next;
    }
    struct Flat *transforming_flat = temp2->flat;
    temp->max_bandwidth += temp2->max_bandwidth;
    temp->instant_bandwidth += temp2->instant_bandwidth;
    while (temp->flat->next != NULL)
    {
        temp->flat = temp->flat->next;
    }
    if (temp2->flat == NULL)
    {
        while (temp->flat->prev != NULL)
        {
            temp->flat = temp->flat->prev;
        }
        remove_apartment(apt2);
        return;
    }

    temp->flat->next = transforming_flat;
    transforming_flat->prev = temp->flat;

    while (temp->flat->prev != NULL)
    {
        temp->flat = temp->flat->prev;
    }

    remove_apartment(apt2);
}

/*void relocate_flats_to_same_apt(string apt_name, int flat_id_to_shift, string flat_id_list)   // This function is not completed 
{
    struct Apartment *temp = head_ap;
    struct Apartment *temp2 = head_ap;
    flat_id_list = flat_id_list.substr(1, flat_id_list.length() - 2);
    int length_flat_arr = flat_id_list.length() / 2;
    int flat_arr[length_flat_arr];
    int j = 0;
    int flat;

    stringstream ss(flat_id_list); // Turning string list into integer array.

    for (int i; ss >> i;)
    {
        flat_arr[j] = i;
        if (ss.peek() == ',')
            ss.ignore();
        j++;
    }

    int index = length_flat_arr - 1;
    do
    {
        
        if (temp->flat != NULL)
        {

            while (temp2->name != apt_name)
            {
                temp2 = temp2->next;
            }
            if (temp2->flat != NULL)
            {
                while (temp2->flat->id != flat_id_to_shift)
                {
                    temp2->flat = temp2->flat->next;
                }
                flat_id_to_shift = flat_arr[index];
            }
            while (temp->flat->id != flat_arr[index])
            {
                if (temp->flat->next == NULL)
                {

                    while (temp->flat->prev != NULL)
                    {
                        temp->flat = temp->flat->prev;
                    }
                    temp = temp->next;
                }
                else
                {
                    temp->flat = temp->flat->next;
                }
            }
            
            if (temp2->flat->prev != NULL)
            {      
                temp2->flat->prev->next = temp->flat;  
                if (temp->flat->prev != NULL)
                {
                    temp->flat->prev->next = temp->flat->next;
                    temp->flat->next->prev = temp->flat->prev;
                }
                else if(temp->flat->prev == NULL){
                    temp->flat->next->prev = NULL;
                }
                
                temp->flat->next = temp2->flat;
                temp->flat->prev = temp2->flat->prev;
                temp2->flat->prev = temp->flat;
            }
            
            else if(temp2->flat->prev == NULL){
                Flat *x = temp->flat->prev;
                if (temp->flat->prev != NULL)
                {
                    
                    if (temp->flat->next != NULL)
                    {
                        
                        temp->flat->prev->next = temp->flat->next;
                        temp->flat->prev = NULL;
                        temp->flat->next->prev = temp->flat->prev;
                        temp->flat->next = NULL;
                       
                    }
                    else
                    {
                        temp->flat->prev->next = temp->flat->next;
                        temp->flat->prev = NULL;
                        temp->flat->next = NULL;
                    }
                    
                }
                  
                else if(temp->flat->prev == NULL){
                    if (temp->flat->next != NULL)
                    {
                       temp->flat->next->prev = NULL; 
                    }
                    
                }
                
                Flat *tempflat = temp->flat;
                
               
                temp2->flat->prev = tempflat->prev;
                temp2->flat->prev = tempflat;
        
            }
            
            if (temp->flat != NULL)
            {
                while (temp->flat->prev != NULL)
                {
                    temp->flat = temp->flat->prev;
                }
            }
            if (temp2->flat != NULL)
            {
                while (temp2->flat->prev != NULL)
                {
                    temp2->flat = temp2->flat->prev;
                }
            }
            
            temp = head_ap;
            index--;
        }
        else
        {
            temp = temp->next;
        }

    } while (temp->next != head_ap && index >= 0);
} */
void outputfile(ofstream& output)  // Writes the conclusions into output.txt.
{
    
    struct Apartment *temp = head_ap;
    if (!temp)
    {
        output << "There is no apartment!";
        return;
    }
    do
    {
        output << temp->name << "(" << temp->max_bandwidth << ")\t";
        while (temp->flat != NULL)
        {
            output << "Flat" << temp->flat->id << "(" << temp->flat->initial_bandwidth << ")"
                   << "\t";
            if (temp->flat->next == NULL)
            {
                break;
            }
            else
            {
                temp->flat = temp->flat->next;
            }
        }
        if (temp->flat != NULL)
        {
            while (temp->flat->prev != NULL)
            {
                temp->flat = temp->flat->prev;
            }
        }
        output << endl;
        temp = temp->next;
        if (temp->next == head_ap)
        {
            output << temp->name << "(" << temp->max_bandwidth << ")\t";
            while (temp->flat != NULL)
            {
                output << "Flat" << temp->flat->id << "(" << temp->flat->initial_bandwidth << ")"
                       << "\t";
                if (temp->flat->next == NULL)
                {
                    break;
                }
                else
                {
                    temp->flat = temp->flat->next;
                }
            }
            if (temp->flat != NULL)
            {
                while (temp->flat->prev != NULL)
                {
                    temp->flat = temp->flat->prev;
                }
            }

            output << "\n"
                   << endl; // unutma burayııı
            break;
        }
        if (temp->flat != NULL)
        {
            while (temp->flat->prev != NULL)
            {
                temp->flat = temp->flat->prev;
            }
        }

    } while (temp->next != head_ap);
}

int main(int argc, char *argv[])
{

    ifstream cmd(argv[1]); 
    ifstream cmd2(argv[1]);
    ofstream output(argv[2]); // Creating output.txt file.

    string txt;
    string txt2;
    int y = 0;
    while (cmd >> txt)
    {
        int x = 0;

        string commands[4];
        if (txt == "add_apartment")
        {
            while (x < 4 && cmd2 >> txt2)
            {
                commands[x] = txt2;
                x++;
            }
            int bandwith;
            stringstream ss(commands[3]);
            ss >> bandwith;
            add_apartment(commands[1], commands[2], bandwith);
        }

        else if (txt == "add_flat")
        {
            cmd2 >> txt2;
            while (x < 4 && cmd2 >> txt2)
            {
                commands[x] = txt2;
                x++;
            }
            int index;
            int bandwith;
            int id;
            stringstream ind(commands[1]);
            ind >> index;
            stringstream bw(commands[2]);
            bw >> bandwith;
            stringstream id_str(commands[3]);
            id_str >> id;
            add_flat(commands[0], index, bandwith, id);
        }
        else if (txt == "remove_apartment")
        {
            while (x < 2 && cmd2 >> txt2)
            {
                commands[x] = txt2;
                x++;
            }
            remove_apartment(commands[1]);
        }

        else if (txt == "make_flat_empty")
        {
            while (x < 3 && cmd2 >> txt2)
            {
                commands[x] = txt2;
                x++;
            }
            int id;
            stringstream id_str(commands[2]);
            id_str >> id;
            make_flat_empty(commands[1], id);
        }

        else if (txt == "find_sum_of_max_bandwidths")
        {
            cmd2 >> txt2;
            find_sum_of_max_bandwith(output);
        }
        else if (txt == "merge_two_apartments")
        {
            while (x < 3 && cmd2 >> txt2)
            {
                commands[x] = txt2;
                x++;
            }

            merge_two_apartments(commands[1], commands[2]);
        }
        else if (txt == "relocate_flats_to_same_apartment")
        {
            while (x < 4 && cmd2 >> txt2)
            {
                commands[x] = txt2;
                x++;
            }
            int id;
            stringstream id_str(commands[2]);
            id_str >> id;
            // relocate_flats_to_same_apt(commands[1], id, commands[3]);
        }
        else if (txt == "list_apartments")
        {
            cmd2 >> txt2;
            outputfile(output);
        }
    }
    
}