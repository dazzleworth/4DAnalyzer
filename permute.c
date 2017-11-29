#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#include "ResultAnalyzerGateway.h"

#define MAX_DRAWS 23
#define PERMUT 4

#define digit_one 0x08
#define digit_two 0x04
#define digit_three 0x02
#define digit_four 0x01

void doPermute(char **);
int permute(char [], char [], int);
void swap(char *, int, int);
unsigned char compare(char*,char *);
int is12Permut(char *);
int is4Permut(char *);
void freeAll(char **, int);

const char TOKENS[] = " ,[]";

JNIEXPORT void JNICALL Java_ResultAnalyzerGateway_runAnalyseData
  (JNIEnv *env, jobject JObject, jstring message)
{
	(*env)->MonitorEnter(env, JObject);

	jboolean copied;

	char **digits;

	digits = (char**) malloc(sizeof(char*) * MAX_DRAWS);

	int i = 0;

	char* drawData = (*env)->GetStringUTFChars(env, message, &copied );

	char *token;

	token = strtok(drawData, TOKENS);

	while(token != NULL) {
		digits[i] = (char*) malloc(PERMUT + 1);
		strncpy(digits[i++], token, PERMUT + 1);
		token = strtok(NULL, TOKENS);
	}

	doPermute(digits);

	freeAll(digits, i);

	(*env)->ReleaseStringUTFChars(env, message, drawData);
	(*env)->MonitorExit(env, JObject);
}

JNIEXPORT jboolean JNICALL Java_ResultAnalyzerGateway_checkContainsPermutation
  (JNIEnv *env, jobject obj, jstring digits, jstring base)
{
	
	char * ds = (*env)->GetStringUTFChars(env, digits, NULL );

	char * dsCopy = malloc( strlen(ds) + 1);

	strcpy(dsCopy, ds);

	char * bs = (*env)->GetStringUTFChars(env, base, NULL );

	int res = permute(dsCopy, bs, 0);

	free(dsCopy);

	(*env)->ReleaseStringUTFChars(env, digits, ds);
	(*env)->ReleaseStringUTFChars(env, base, bs);

	return (res) ? JNI_TRUE : JNI_FALSE;
}

void freeAll(char **buf, int n)
{
	int i;

	for(i = 0; i < n; i++){
		free(buf[i]);
	}

	free(buf);
}

void doPermute(char **data)
{
	int i, j;

	for(i = 0; i < MAX_DRAWS; i++) {
		if(is12Permut(data[i])  && (!is4Permut(data[i]))) {
			printf("Found 12-permutation: %s  ", data[i]);
			printf("[ ");
			for(j = 0; j < MAX_DRAWS; j++) {
				if(i == j) continue;



				if(!is12Permut(data[j]))		//remove if looking for 3-base numbers that are NOT 12-permutations
					if(permute(data[i], data[j], 0))
						printf("%s, ", data[j]);
			}
			printf("]\n");
			fflush(stdout);
		}
	}
}

void swap(char *str, int i, int j)
{
    char temp = str[i];
    str[i] = str[j];
    str[j] = temp;
}

int permute(char  string[], char cmpstr[], int start)
{

    if(start == PERMUT)
    {
		if(__builtin_popcount(compare(string, cmpstr)) == PERMUT - 1)
			return 1;

		return 0;
    }

    int res = permute(string, cmpstr, start + 1);
    if(res) return res;
    int i;
    for(i = start + 1; i < PERMUT; i++)
    {
        if(string[start] == string[i])
            continue;
        swap(string, start, i);
        res = permute(string, cmpstr, start + 1);
	if(res) break;
        swap(string, start, i);
    }

    return res;

}

unsigned char compare(char*s, char*t)
{
	unsigned char h = 0;

	if(s[0] == t[0]) h |= digit_one;
	if(s[1] == t[1]) h |= digit_two;
	if(s[2] == t[2]) h |= digit_three;
	if(s[3] == t[3]) h |= digit_four;

	return h;
}

int is12Permut(char *num)
{
	int c = 0;

	if(num[0] == num[1]) c++;
	if(num[0] == num[2]) c++;
	if(num[0] == num[3]) c++;

	//if(c > 0 && c < 2) return c;

	if(num[1] == num[0]) c++;
	if(num[1] == num[2]) c++;
	if(num[1] == num[3]) c++;

	//if(c > 0 && c < 2) return c;

	if(num[2] == num[0]) c++;
	if(num[2] == num[1]) c++;
	if(num[2] == num[3]) c++;

	//if(c > 0 && c < 2) return c;

	return (c == 1)? 1 : 0;
}

int is4Permut(char *num)
{
	int c = 0;

	if(num[0] == num[1]) c++;
	if(num[0] == num[2]) c++;
	if(num[0] == num[3]) c++;

	if(c == 2) return 1;

	if(num[1] == num[0]) c++;
	if(num[1] == num[2]) c++;
	if(num[1] == num[3]) c++;

	if(c == 2) return 1;

	if(num[2] == num[0]) c++;
	if(num[2] == num[1]) c++;
	if(num[2] == num[3]) c++;

	if(c == 2) return 1;

	return 0;
}
