#include <iostream>
#include <fstream>
#include <vector>
#include <bits/stdc++.h>#include <bits/stdc++.h>
#include <math.h>

using namespace std;

struct coffe {   // This struct represents the orders that are taken by input.
    float price;
    float entrance;
    float order_dur;
    float prep_dur;
    float turnaorund;
    coffe *next;
};

struct Cashier {  // This struct's aim is to create a cashier object with order attributes and its own attributes like ID.
    int ID;
    float total_time;
    float entrance;
    float order_dur;
};

struct Barista { // This struct's aim is to create a barista object with order attributes and its own attributes like ID.
    int ID;
    float prep_time;
    float total_time;
    float entrance;
};

class Order {    /* This class is the class where enqueueing, dequeueing and some other functions are created.
                    In order to have two different queues(cashier and barista) it is useful. Also, it uses coffe struct to create queues.*/

public:
    coffe *first, *last;

    Order() { last = NULL, first = NULL; }

    void enqueue(float entrance_time, float order_dur, float prep_dur, float price) { // Enqueue function for cashier queue. First comes first serve.
        coffe *newOrder = new coffe;
        newOrder->price = price;
        newOrder->entrance = entrance_time;
        newOrder->order_dur = order_dur;
        newOrder->prep_dur = prep_dur;

        if (last == NULL && first == NULL) {
            first = last = newOrder;
            first->next = NULL;
            last->next = NULL;
            return;
        }
        last->next = newOrder;
        last = last->next;
        last->next = NULL;
    }

    void enqueueb(float entrance_time, float order_dur, float prep_dur, float price, float turnaorund) { // Enqueue method for barista queue. Depends on the cashier queue.
        coffe *newOrder = new coffe;
        newOrder->price = price;
        newOrder->entrance = entrance_time;
        newOrder->order_dur = order_dur;
        newOrder->prep_dur = prep_dur;
        newOrder->turnaorund = turnaorund;

        coffe *temp = first;
        if (last == NULL && first == NULL) {
            first = last = newOrder;
            first->next = NULL;
            last->next = NULL;
            return;
        } else if (last->order_dur + last->entrance < newOrder->entrance + newOrder->order_dur) {
            last->next = newOrder;
            last = last->next;
            last->next = NULL;
            return;
        } else {
            while (temp != NULL) {
                if ((temp->entrance + temp->order_dur < newOrder->entrance + newOrder->order_dur)
                    && (newOrder->entrance + newOrder->order_dur < temp->next->entrance + temp->next->order_dur)) {
                    newOrder->next = temp->next;
                    temp->next = newOrder;
                    break;
                } else {
                    temp = temp->next;
                }
            }
        }

    }


    void dequeue() {                        // Dequeu function for cashier queue. When the order is trasnfered to the cashier object.
                                            // This function is called by AddOrder function and deletes that order.
        if (first == NULL && last == NULL)
            return;
        coffe *temp = first;
        if (first->next == NULL) {
            first = last = NULL;
            free(temp);
            return;
        }

        first = first->next;
        free(temp);
        return;


    }

    void dequeue2(float f) {                  // Dequeu function for barista queue. When the order is trasnfered to the barista object.
                                              // This function is called by AddPrep function and deletes that order.
        if (first == NULL && last == NULL)
            return;
        coffe *temp = first;
        coffe *temp2 = NULL;
        while (temp->price != f) {
            temp2 = temp;
            temp = temp->next;
        }
        if (temp2 == NULL && temp->next == NULL) {
            first = last = NULL;
            free(temp);
            return;
        } else if (temp2 == NULL && temp->next != NULL) {
            first = first->next;
            temp = NULL;
            free(temp);
            return;
        } else {
            temp2->next = temp->next;
            temp = NULL;
            free(temp);
            return;
        }

    }

    int countQueue() {   // This function is used for getting maximum length of queues. It works with AddOrder() and AddPrep() functions.
        int count = 0;
        coffe *temp = first;

        while (temp != NULL) {
            count++;
            temp = temp->next;
        }
        return count;
    }
};

int ischng = 0;

void
addOrder(vector<Cashier> &cashier, vector<float> v, vector<float> &v2, int i, int j, int &c, Order &o1, Order &o2) { /* This function is transferring the order node's attribute
                                                                                                                        to cashier object. Then calling another functions, it deletes
                                                                                                                        the order from cashier queue.*/
    if (j == cashier.size() || i == v.size()) {
        c++;
        return;
    } else if (cashier[j].order_dur > v[i]) {
        addOrder(cashier, v, v2, i, j + 1, c, o1, o2);
        return;
    }
    float frst;
    if (o1.countQueue() > 1) {
        vector<float> times;
        int k = 0;
        while (k < cashier.size()) {
            times.push_back(cashier[k].order_dur);
            k++;
        }
        sort(times.begin(), times.end());
        k = 0;
        int m = 0;
        while (o1.first->entrance > times[m]) {
            m++;
        }
        ischng = m;
        while (k != j)
            k++;
        while (cashier[j].order_dur != times[k])
            j++;
        cashier[j].order_dur += o1.first->order_dur;
        cashier[j].entrance = o1.first->entrance;
        frst = times[0];
    } else if (o1.countQueue() <= 1) {
        cashier[j].order_dur = o1.first->entrance + o1.first->order_dur;
        cashier[j].entrance = o1.first->entrance;
    }

    if (cashier[j].entrance == v2[0]) {
        cashier[j].total_time += o1.first->order_dur;
        o2.enqueueb(o1.first->entrance, o1.first->order_dur, o1.first->prep_dur, o1.first->price, o1.first->order_dur);
        v2.erase(v2.begin());


    }
    o1.dequeue();
    if (o1.first != NULL && o1.first->entrance > frst)
        addOrder(cashier, v, v2, i, j + 1, c, o1, o2);
    return;

}

bool isGreater(coffe *c, Order o) {     // Comparison function to compare available situation for baristas at some point.
    coffe *tmp = o.first;
    while (tmp != c) {
        if (c->entrance + c->order_dur > tmp->entrance + tmp->order_dur)
            return true;
        tmp = tmp->next;
    }
    return false;
}

int ischanged = 0;
int ischanged2 = 0;
vector<float> turnarounds;

void
addPrep(vector<Barista> &barista, vector<float> v, vector<float> &v3, vector<float> &v4, int j, int c, int &d, Order &o,/* This function is transferring the order node's attribute
                                                                                                                        to barista object. Then calling another functions, it deletes
                                                                                                                        the order from barista queue.*/
        coffe *coff) {
    if (j == barista.size()) {
        if (find(v4.begin(), v4.end(), coff->price) != v4.end()) {

        } else {
            v4.push_back(coff->price);
            sort(v4.begin(), v4.end(), greater<float>());
        }
        d--;
        return;
    } else if ((barista[j].prep_time > coff->entrance + coff->order_dur)) {
        addPrep(barista, v, v3, v4, j + 1, c, d, o, coff);
        return;
    }
    float turnaround;
    if (c <= barista.size()) {
        coffe *tempo = o.first;
        barista[j].prep_time = tempo->entrance + tempo->order_dur + tempo->prep_dur;
        barista[j].total_time += tempo->prep_dur;
        barista[j].entrance = tempo->entrance + tempo->order_dur;
        tempo->turnaorund += tempo->prep_dur;
        turnarounds[c - 1] += tempo->prep_dur;
        o.dequeue2(tempo->price);
    } else {

        if (1 < ischanged && ischanged <= barista.size()) {
            j++;
            if (j == barista.size() - 1)
                ischanged = 0;
        } else if (ischanged == 1)
            ischanged++;
        coffe *tempo = o.first;
        coffe *tempo2 = o.first;
        while (tempo->price != v4[0])
            tempo = tempo->next;
        barista[j].prep_time += tempo->prep_dur;
        barista[j].total_time += tempo->prep_dur;
        tempo->turnaorund += tempo->prep_dur;
        turnaround = tempo->prep_dur;
        o.dequeue2(tempo->price);
        v4.erase(v4.begin());
        if (find(v4.begin(), v4.end(), coff->price) != v4.end()) {

        } else {
            if (o.countQueue() == 1 || o.countQueue() == 0 || ischanged2 > 0) {
                return;
            }
            v4.push_back(coff->price);
            if (isGreater(coff, o) == false || coff->next == NULL)
                sort(v4.begin(), v4.end(), greater<float>());
        }
        return;
    }

}

bool compareByPrep(const Barista &a, const Barista &b) {
    return a.prep_time < b.prep_time;
}

bool compareByID(const Barista &a, const Barista &b) {
    return a.ID < b.ID;
}


int main(int argc, char *argv[]) {
    ifstream inputFile(argv[1]);
    ofstream output(argv[2]);

    int num_of_cashiers;
    inputFile >> num_of_cashiers; // Getting cashier number.
    int num_of_orders;
    inputFile >> num_of_orders; // Getting order number.


    float num;
    vector<float> v;
    while (inputFile >> num) {
        v.push_back(num);

    }

    vector<Cashier> cashiers;
    vector<Barista> baristas;
    int count = 1;
    while (count < num_of_cashiers + 1) {
        Cashier c;
        c.ID = count;
        c.order_dur = 0;
        c.total_time = 0;
        cashiers.push_back(c);
        count++;
    }
    for (int i = 1; i < num_of_cashiers / 3 + 1; ++i) {
        Barista b;
        b.ID = i;
        b.prep_time = 0;
        b.total_time = 0;
        b.entrance = 0;
        baristas.push_back(b);
    }
    Order cQ;
    Order bQ;
    vector<float> v2;
    int index = 0;
    for (int i = 0; i < v.size(); i = i + 4) {
        v2.push_back(v[i]);
    }
    sort(v2.begin(), v2.end());

    index = 0;
    vector<float> v3;
    for (int i = 3; i < v.size(); i = i + 4) {
        v3.push_back(v[i]);
    }
    sort(v3.begin(), v3.end(), greater<float>());

    int max_lengthcQ = 0;
    for (int i = 0; i < v.size(); i = i + 4) {
        cQ.enqueue(v[i], v[i + 1], v[i + 2], v[i + 3]);
        addOrder(cashiers, v, v2, i, 0, max_lengthcQ, cQ, bQ);
        if (cQ.countQueue() > 0 && i == v.size() - 4) {
            int a = cQ.countQueue();
            while (a > 0) {
                addOrder(cashiers, v, v2, i, 0, max_lengthcQ, cQ, bQ);
                a--;
            }
        }
    }
    coffe *tempo2 = bQ.first;
    while (tempo2 != NULL) {
        turnarounds.push_back(tempo2->order_dur);
        tempo2 = tempo2->next;
    }
    int first_to_n_over_3 = 1;
    vector<float> v4;
    coffe *temp = bQ.first;
    int max_lengthbQ = num_of_orders;
    while (temp != NULL) {
        addPrep(baristas, v, v3, v4, 0, first_to_n_over_3, max_lengthbQ, bQ, temp);
        first_to_n_over_3++;
        temp = temp->next;
        if (bQ.countQueue() > 0 && temp == NULL) {
            decltype(baristas)::iterator minEl, maxEl;
            tie(minEl, maxEl) = minmax_element(begin(baristas), end(baristas),
                                               [](Barista const &s1, Barista const &s2) {
                                                   return s1.prep_time < s2.prep_time;
                                               });

            for (Barista &b: baristas) {
                if (b.prep_time == minEl->prep_time)
                    continue;
                b.prep_time -= minEl->prep_time;

            }
            minEl->prep_time = 0;
            sort(baristas.begin(), baristas.end(), compareByPrep);
            ischanged++;
            ischanged2++;

            temp = bQ.first;
        } else if (bQ.countQueue() == 0)
            break;
    }
    sort(baristas.begin(), baristas.end(), compareByID);


    float trt = (baristas[0].total_time + baristas[0].entrance);
    output << trt << endl;
    output << max_lengthcQ << endl;
    output << max_lengthbQ << endl;
    for (Cashier c: cashiers) {
        float utilization = (int) (c.total_time / trt * 100.0 + .5) / 100.0;
        output << fixed << setprecision(2) << (utilization) << endl;
    }
    for (Barista b: baristas) {
        float utilization = (int) (b.total_time / trt * 100.0 + .5) / 100.0;
        output << fixed << setprecision(2) << (utilization) << endl;
    }

    for (int i = 0; i < v.size(); i = i + 4) {

        if (v[i + 1] + v[i + 2] == turnarounds[0]) {
            output << fixed << setprecision(2) << turnarounds[0] << endl;
            turnarounds.erase(turnarounds.begin());

        }
        else
        {
            output << -1 << endl;
        }
    }
    int m2 = num_of_orders+num_of_cashiers+((num_of_cashiers/3)*2) + 2; // I didn't make model2.
    output << endl;
    for (int i = 0; i < m2; ++i) {
            output << -1 << endl;
    }


    }
