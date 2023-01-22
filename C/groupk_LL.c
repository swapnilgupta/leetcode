#include <stdio.h>
#include <stdlib.h>

/* Link list node */
struct node {
  int data;
  struct node *next;
};

struct node *reverse(struct node *head, int k) {
  struct node *prev, *curr, *next;
  curr = head;
  prev = NULL;
  next = NULL;
  int count = 0;
  while (curr != NULL && count < k) // rev
  {
    next = curr->next;
    curr->next = prev;
    prev = curr;
    curr = next;
    count++;
  }

  if (next != NULL)
    head->next = reverse(next, k);
  return prev;
}

void push(struct node **head_ref, int new_data) {
  /* allocate node */
  struct node *new_node = (struct node *)malloc(sizeof(struct node));

  /* put in the data  */
  new_node->data = new_data;

  /* link the old list off the new node */
  new_node->next = (*head_ref);

  /* move the head to point to the new node */
  (*head_ref) = new_node;
}

/* Function to print linked list */
void printList(struct node *node) {
  while (node != NULL) {
    printf("%d  ", node->data);
    node = node->next;
  }
}

int main(void) {
  /* Start with the empty list */
  struct node *head = NULL;

  /* Created Linked list is 1->2->3->4->5->6->7->8 */
  push(&head, 8);
  push(&head, 7);
  push(&head, 6);
  push(&head, 5);
  push(&head, 4);
  push(&head, 3);
  push(&head, 2);
  push(&head, 1);

  printf("\n Given linked list \n");
  printList(head);
  head = reverse(head, 3);

  printf("\n Reversed Linked list \n");
  printList(head);

  getchar();
  return (0);
}
