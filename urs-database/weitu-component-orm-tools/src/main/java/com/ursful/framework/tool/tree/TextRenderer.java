package com.ursful.framework.tool.tree;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;public class TextRenderer extends DefaultTreeCellRenderer {

    public TextRenderer() {
 }
 public Component getTreeCellRendererComponent(JTree tree, Object value,
   boolean sel, boolean expanded, boolean leaf, int row,
   boolean hasFocus) {
  super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
    row, hasFocus);

    // this.setBounds(0,0, tree.getWidth(), 30);
     //Icon test = new ImageIcon("./images/test.jpg");
     this.setIcon(null);
//  Icon test1 = new ImageIcon("./images/test1.jpg");
//  Icon test2 = new ImageIcon("./images/test2.jpg");
//  Icon test3 = new ImageIcon("./images/test3.jpg");  if (leaf) {
//   DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
//   String str = value.toString();
//   if (str.equals("自由飞翔")) {
//    this.setIcon(test1);
//   } else if (str.equals("我行我素")) {
//    this.setIcon(test2);
//   } else {
//    this.setIcon(test3);
//   }  } else {
//   if (expanded) {
//    this.setIcon(test);
//   } else {
//    this.setIcon(test);
//   }
//  }
  this.setText(value.toString());
  return this;
 }}