#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
using namespace std;

int matrixproduct(int *arr1, int *arr2, int total,
                   int rw, int cl, int col, int x, int y)// This function makes the cross product  between keymatrix and submapmatrix.
{                                                                                                  
   for (int i = 0, k = x; i < rw; i++, k++)
   {

      for (int j = 0, l = y; j < cl; j++, l++)
      {

         total += *(arr1 + i * cl + j) * (*(arr2 + k * col + l));
         
      }
   
   }
   
   
   while (total <0)
   {
      total+=5;
   }
   
   return total;
}

void checkDirection(int pos_x, int pos_y, int tot, int key_size, int row, int col, // This function makes the decision according to the total value and recursively calls.
                  int *arr1, int *arr2, int rw, int cl,int mid_x, int mid_y, ofstream& file)
{
   
   if (tot % 5 == 1)
   {
      if (pos_x == 0)
      {
         pos_x += key_size;
         mid_x += key_size;
         tot = 0;
         tot = matrixproduct(arr1, arr2, tot, rw, cl, col, pos_x, pos_y);
         file << mid_x <<","<< mid_y <<":" << tot <<"\n";
         checkDirection(pos_x, pos_y, tot, key_size, row, col, arr1, arr2, rw, cl, mid_x,mid_y,file);
      }
      else{
         pos_x -= key_size;
         mid_x -= key_size;
         tot = 0;
         tot = matrixproduct(arr1, arr2, tot, rw, cl, col, pos_x, pos_y);
         file << mid_x <<","<< mid_y <<":" << tot <<"\n";
         checkDirection(pos_x, pos_y, tot, key_size, row, col, arr1, arr2, rw, cl, mid_x,mid_y,file);
      }
      
   }
   else if (tot % 5 == 2)
   {
      
      if (pos_x == row-key_size)
      { 
         pos_x -= key_size;
         mid_x -= key_size;
         tot = 0;
         tot = matrixproduct(arr1, arr2, tot, rw, cl, col, pos_x, pos_y);
         file << mid_x <<","<< mid_y <<":" << tot <<"\n";
         checkDirection(pos_x, pos_y, tot, key_size, row, col, arr1, arr2, rw, cl, mid_x,mid_y,file);
      }
      else
      {
         pos_x += key_size;
         mid_x += key_size;
         tot = 0;
         tot = matrixproduct(arr1, arr2, tot, rw, cl, col, pos_x, pos_y);
         file << mid_x <<","<< mid_y <<":" << tot <<"\n";
         checkDirection(pos_x, pos_y, tot, key_size, row, col, arr1, arr2, rw, cl, mid_x,mid_y,file);
      }
   }
   else if (tot % 5 == 3)
   {
      if (pos_y == col -key_size)
      {
         pos_y -= key_size;
         mid_y -= key_size;
         tot = 0;
         tot = matrixproduct(arr1, arr2, tot, rw, cl, col, pos_x, pos_y);
         file << mid_x <<","<< mid_y <<":" << tot <<"\n";
         checkDirection(pos_x, pos_y, tot, key_size, row, col, arr1, arr2, rw, cl, mid_x,mid_y,file);
      }
      else
      {
         pos_y += key_size;
         mid_y += key_size;
         tot = 0;
         tot = matrixproduct(arr1, arr2, tot, rw, cl, col, pos_x, pos_y);
         file << mid_x <<","<< mid_y <<":" << tot <<"\n";
         checkDirection(pos_x, pos_y, tot, key_size, row, col, arr1, arr2, rw, cl, mid_x,mid_y,file);
      }
   }
   else if (tot % 5 == 4)
   {
      if (pos_y == 0)
      {
         pos_y += key_size;
         mid_y += key_size;
         tot = 0;
         tot = matrixproduct(arr1, arr2, tot, rw, cl, col, pos_x, pos_y);
         file << mid_x <<","<< mid_y <<":" << tot <<"\n";
         checkDirection(pos_x, pos_y, tot, key_size, row, col, arr1, arr2, rw, cl, mid_x,mid_y,file);
      }
      else
      {
         pos_y -= key_size;
         mid_y -= key_size;
         tot = 0;
         tot = matrixproduct(arr1, arr2, tot, rw, cl, col, pos_x, pos_y);
         file << mid_x <<","<< mid_y <<":" << tot <<"\n";
         checkDirection(pos_x, pos_y, tot, key_size, row, col, arr1, arr2, rw, cl, mid_x,mid_y,file);
      }
      
   }
   
}

int main(int argc, char *argv[])
{

   int row;
   int col;
   int size_key; // This part is for turning arguments into int data type.
   string arg1 = argv[1];
   string arg2 = argv[2];
   stringstream s1(arg1.substr(0,2));
   s1 >> row;
   stringstream s2(arg1.substr(3,2));
   s2 >> col;
   stringstream s3 (arg2);
   s3 >> size_key;

   int *map_arr = new int[row * col];

   ifstream mapmatrix(argv[3]);

   string txt;
   int x =0;
   int y = 0;
   while (mapmatrix >> txt) // This loop reads the mapmatrix.txt file and put it into dynamic 2d array.
   {
      stringstream ss(txt);
      int a = 0;
      ss >> a;

      *(map_arr + y * col + x) = a;
      x++;
      if (txt == " ")
      {
         y++;
      }
   }
   

   ifstream keymatrix(argv[4]); 
   int rw = size_key;
   int cl = size_key;

   int *key_arr = new int[rw * cl];

   string txt2;
   int a, b = 0;

   while (keymatrix >> txt2) //This loop reads keymatrix.txt file and put it into 2d dynamic array.
   {
      stringstream ss2(txt2);
      int c = 0;
      ss2 >> c;

      *(key_arr + b * col + a) = c;
      a++;
      if (txt2 == " ")
      {
         b++;
      }
   }

   

   ofstream output(argv[5]); // Creating output.txt file.

   int total = 0;
   int mid_x = (size_key - 1) / 2; // Mid positions of the submapmatrix.
   int mid_y = (size_key - 1) / 2;

   total = matrixproduct(key_arr, map_arr, total, rw, cl, col, 0, 0);
   output << mid_x <<","<< mid_y <<":" << total << endl;
   checkDirection(0, 0, total, size_key, row, col, key_arr, map_arr, rw, cl,mid_x,mid_y,output);
   
   

}