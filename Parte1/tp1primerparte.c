#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//#define MAX 5
#define MAX 7
//#define MAX 9

typedef struct nodo{
    char palabra[MAX];
    int apariciones;
    struct nodo *sig;
}nodo;
typedef nodo*tlista;

void cargoLista(tlista*);
void muestraLista(tlista);
void agregaLista(tlista*,char[]);

int main (){
    tlista L=NULL;

    cargoLista(&L);
    muestraLista(L);

    return 0;
}


void cargoLista(tlista *L){
    FILE* arch;
    char digito;
    char palabra[MAX];
    int cuentaAux=0,longitud;
    arch=fopen("anexo1-grupo8.txt","rt");
    if(arch==NULL)
        printf("Error al abrir el archivo.\n");
    else{
        while (fscanf(arch,"%c",&digito)==1){
            longitud=0;
            palabra[longitud++]=digito;
            while(longitud<MAX){
                fscanf(arch,"%c",&digito);
                palabra[longitud]=digito;
                longitud++;
            }
            palabra[MAX]='\0';
            agregaLista(L,palabra);
            cuentaAux++;
        }
        fclose(arch);
    }
    printf("Habia %d PALABRAS CODIGO en el archivo. \n",cuentaAux);
}
void agregaLista(tlista *L,char palabra[]){
    tlista aux=*L,nuevo;
    int encontro=0;
    while(aux!=NULL && !encontro){
        if(strcmp(aux->palabra,palabra)!=0){
            aux=aux->sig;
        }
        else{
            aux->apariciones++;
            encontro=1;
        }
    }  
    if(!encontro){
        nuevo=(tlista)malloc(sizeof(nodo));
        nuevo->sig=*L;
        strcpy(nuevo->palabra,palabra);
        nuevo->apariciones=1;
        *L=nuevo;
    }
}
void muestraLista(tlista L){
    printf("| Palabra | Apariciones |    Probabilidad   |\n");
    while(L!=NULL){
        printf("|  %s  |     %d     |    %5.10f   |\n",L->palabra,L->apariciones,(float)L->apariciones/(31500/MAX));
        L=L->sig;
    }
}