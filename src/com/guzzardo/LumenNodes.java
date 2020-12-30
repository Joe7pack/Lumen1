package com.guzzardo;

import java.util.ArrayList;
import java.util.HashMap;

public class LumenNodes {

    private final HashMap <Integer,  ArrayList<Node>> treeList = new HashMap<>();
    private ArrayList<Node> siblings;
    private Integer currentParent;

    public void listChildren() {
        for (Integer keyValue : treeList.keySet()) {
            ArrayList<Node> nodeList = treeList.get(keyValue);
            for (Node node : nodeList) {
                System.out.println(node.toString());
            }
        }
    }

    private void addSibling(Node node) {
        if (!node.parentId.equals(currentParent)) {
            siblings = new ArrayList<>();
            siblings.add(node);
            treeList.put(node.parentId, siblings);
            currentParent = node.parentId;
        } else {
            siblings.add(node);
        }
    }

    private static class Node {
        Integer parentId;
        Integer nodeId;
        String nodeName;

        @Override
        public String toString() {
            return "Node{" +
                    "parentId='" + parentId + '\'' +
                    ", nodeId='" + nodeId + '\'' +
                    ", nodeName='" + nodeName + '\'' +
                    '}';
        }
    }

    private void createNode(String[] nodeValues) {
        if (nodeValues.length < 3)
            return;
        Node newNode = new Node();
        if (nodeValues[0].equals("null")) {
            newNode.parentId = -1;
        } else {
            newNode.parentId = Integer.valueOf(nodeValues[0]);
        }
        newNode.nodeId = Integer.valueOf(nodeValues[1]);
        newNode.nodeName = nodeValues[2];
        addSibling(newNode);
    }

    public static void main(String[] args) {
        String inputString = (args[0]);
        LumenNodes ln = new LumenNodes();
        String[] arrOfStr = inputString.split("\\|");

        for (String a : arrOfStr) {
            //System.out.println(a);
            String[] nodeValues = a.split(",");
            ln.createNode(nodeValues);
        }
        ln.listChildren();
    }
}
