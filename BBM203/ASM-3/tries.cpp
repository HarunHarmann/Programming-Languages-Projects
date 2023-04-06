#include <iostream>
#include <string.h>
#define R 256
using namespace std;

class Node
{
public:
    int value;
    Node* next[R];
};
Node *root;

Node* getNode(){
    Node* pNode = NULL;
    pNode = new Node;
    if (pNode){
        for (int i = 0; i < R; i++)
            pNode->next[i] = NULL;
    }
    return pNode;
}

void put(Node*& x, char* key, int val, int d)
{
    if (x == NULL)
        x = getNode();
    if (d == strlen(key)) {x->value = val; return;}
    char c = key[d];
    put(x->next[c], key, val, d+1);
}


int main() {
    char* key = "hello";
    int val = 0;
    put(root, key, val, 0);
}
