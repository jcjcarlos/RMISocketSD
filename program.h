#include<stdio.h>
#include<stdlib.h>
#include<time.h>

typedef struct Account{
    int id;
    //char *name;
    double balance;
} Account;


void inicializeBank();
int findAccountById(int id);
int createAccount(int id);
int changeAccount(int id , double value);
int getIdFromUser();
double getValueFromUser();
int listAccount(int id);
void listAccounts();
void showMenu();
void testCriarEListarContas();
void testBuscarConta();
