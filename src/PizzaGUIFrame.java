import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PizzaGUIFrame extends JFrame {
    JPanel topPanel;
    JPanel crustPanel;
    JPanel toppingPanel;
    JPanel sizePanel;
    JPanel orderPanel;
    JPanel bottomPanel;
    JPanel mainPanel;
    JTextArea orderTextArea;
    JScrollPane scroller;
    JRadioButton thin;
    JRadioButton regular;
    JRadioButton deepDish;
    JButton orderButton;
    JButton clearButton;
    JButton quitButton;
    JComboBox sizeBox;
    JCheckBox[] toppingComboBox;
    StringBuilder receipt;

    public PizzaGUIFrame() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createTopPanel();
        createOrderPanel();
        createBottomPanel();

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(orderPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
    }

    private void createTopPanel() {
        topPanel = new JPanel();

        crustPanel = createTitledPanel("Crust");
        thin = new JRadioButton("Thin");
        regular = new JRadioButton("Regular");
        deepDish = new JRadioButton("Deep-dish");

        //Grouping buttons
        ButtonGroup crustGroup = new ButtonGroup();
        crustGroup.add(thin);
        crustGroup.add(regular);
        crustGroup.add(deepDish);

        //Adding crustType to its panel
        crustPanel.add(thin);
        crustPanel.add(regular);
        crustPanel.add(deepDish);

        //Making size panel which is in top panel as well
        sizePanel = createTitledPanel("Size");
        String[] sizes = {"Small","Medium", "Large", "Super"};
        sizeBox = new JComboBox<>(sizes);
        sizePanel.add(sizeBox);

        //Topping panel
        toppingPanel = new JPanel();
        toppingPanel.setLayout(new GridLayout(3,2));
        toppingPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));
        String[] toppings = {"Chicken", "Onion", "Pineapple", "Bacon", "Pepperoni", "Mushroom"};
        toppingComboBox = new JCheckBox[toppings.length];

        for(int i=0;i<toppings.length;i++)
        {
            toppingComboBox[i] = new JCheckBox(toppings[i]);
            toppingPanel.add(toppingComboBox[i]);
        }

        topPanel.add(crustPanel);
        topPanel.add(sizePanel);
        topPanel.add(toppingPanel);
    }

    private void createOrderPanel() {
        orderPanel = createTitledPanel("Order Summary");
        orderTextArea = new JTextArea(20,30);
        orderTextArea.setEditable(false);
        scroller = new JScrollPane(orderTextArea);
        orderPanel.add(scroller);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();

        orderButton = new JButton("Order");
        orderButton.setFont(new Font("Serif", Font.PLAIN, 14));
        orderButton.addActionListener(e -> {
            placeOrder();
        });
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Serif", Font.PLAIN, 14));
        clearButton.addActionListener(e -> {
            clearOrder(sizeBox,toppingComboBox, orderTextArea);
        });

        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Serif", Font.PLAIN, 14));
        quitButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        bottomPanel.add(orderButton);
        bottomPanel.add(clearButton);
        bottomPanel.add(quitButton);

    }

    private static JPanel createTitledPanel(String title) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }
    private void placeOrder()
    {
        //Declaring these variables to make its value reset every single time we order
        double baseCost = 0;
        double ingredientCost =0;
        double totalCost;
        double tax;
        double subTotal;

        receipt = new StringBuilder();
        receipt.append("===============================\n");
        if(thin.isSelected())
        {
            receipt.append("Thin & " + sizeBox.getSelectedItem());
            if(sizeBox.getSelectedItem() == "Small")
            {
                baseCost = 8.00;
                receipt.append("\t\t$" + baseCost);
                for (JCheckBox checkedBox : toppingComboBox)
                {
                    if(checkedBox.isSelected())
                    {
                        receipt.append("\n" + checkedBox.getText() + " \t\t$" + 1.00);
                        ingredientCost+=1;
                    }
                }

                subTotal=baseCost+ingredientCost;
                receipt.append("\n\nSub-total:\t\t$" + subTotal);
                tax = 0.07*subTotal;
                receipt.append("\n\nTax:\t\t$" + String.format("%.2f",tax));
                receipt.append("\n------------------------------------------------------");
                totalCost=subTotal+tax;
                receipt.append("\n\nTotal:\t\t$" + totalCost);
            }
            else if(sizeBox.getSelectedItem() == "Medium")
            {
                baseCost = 12;
                receipt.append("\t\t$" + baseCost);
                for (JCheckBox checkedBox : toppingComboBox)
                {
                    if(checkedBox.isSelected())
                    {
                        receipt.append("\n" + checkedBox.getText() + " \t\t$" + 1.00);
                        ingredientCost+=1;
                    }
                }
                
                subTotal=baseCost+ingredientCost;
                receipt.append("\n\nSub-total:\t\t$" + subTotal);
                tax = 0.07*subTotal;
                receipt.append("\n\nTax:\t\t$" + String.format("%.2f",tax));
                receipt.append("\n------------------------------------------------------");
                totalCost=subTotal+tax;
                receipt.append("\n\nTotal:\t\t$" + totalCost);
            }
            else if(sizeBox.getSelectedItem() == "Large")
            {
                baseCost = 16;
                receipt.append("\t\t$" + baseCost);
                for (JCheckBox checkedBox : toppingComboBox)
                {
                    if(checkedBox.isSelected())
                    {
                        receipt.append("\n" + checkedBox.getText() + " \t\t$" + 1.00);
                        ingredientCost+=1;
                    }
                }
                
                subTotal=baseCost+ingredientCost;
                receipt.append("\n\nSub-total:\t\t$" + subTotal);
                tax = 0.07*subTotal;
                receipt.append("\n\nTax:\t\t$" + String.format("%.2f",tax));
                receipt.append("\n------------------------------------------------------");
                totalCost=subTotal+tax;
                receipt.append("\n\nTotal:\t\t$" + totalCost);
            }
            else
            {
                baseCost = 20;
                receipt.append("\t\t$" + baseCost);
                for (JCheckBox checkedBox : toppingComboBox)
                {
                    if(checkedBox.isSelected())
                    {
                        receipt.append("\n" + checkedBox.getText() + " \t\t$" + 1.00);
                        ingredientCost+=1;
                    }
                }
                
                subTotal=baseCost+ingredientCost;
                receipt.append("\n\nSub-total:\t\t$" + subTotal);
                tax = 0.07*subTotal;
                receipt.append("\n\nTax:\t\t$" + String.format("%.2f",tax));
                receipt.append("\n------------------------------------------------------");
                totalCost=subTotal+tax;
                receipt.append("\n\nTotal:\t\t$" + totalCost);
            }
        }
        else if(deepDish.isSelected())
        {
            receipt.append("Deep-dish & " + sizeBox.getSelectedItem());
            if(sizeBox.getSelectedItem() == "Small")
            {
                baseCost = 8.00;
                receipt.append("\t$" + baseCost);
                for (JCheckBox checkedBox : toppingComboBox)
                {
                    if(checkedBox.isSelected())
                    {
                        receipt.append("\n" + checkedBox.getText() + " \t\t$" + 1.00);
                        ingredientCost+=1;
                    }
                }
                
                subTotal=baseCost+ingredientCost;
                receipt.append("\n\nSub-total:\t\t$" + subTotal);
                tax = 0.07*subTotal;
                receipt.append("\n\nTax:\t\t$" + String.format("%.2f",tax));
                receipt.append("\n------------------------------------------------------");
                totalCost=subTotal+tax;
                receipt.append("\n\nTotal:\t\t$" + totalCost);
            }
            else if(sizeBox.getSelectedItem() == "Medium")
            {
                baseCost = 12;
                receipt.append("\t$" + baseCost);
                for (JCheckBox checkedBox : toppingComboBox)
                {
                    if(checkedBox.isSelected())
                    {
                        receipt.append("\n" + checkedBox.getText() + " \t\t$" + 1.00);
                        ingredientCost+=1;
                    }
                }
                
                subTotal=baseCost+ingredientCost;
                receipt.append("\n\nSub-total:\t\t$" + subTotal);
                tax = 0.07*subTotal;
                receipt.append("\n\nTax:\t\t$" + String.format("%.2f",tax));
                receipt.append("\n------------------------------------------------------");
                totalCost=subTotal+tax;
                receipt.append("\n\nTotal:\t\t$" + totalCost);
            }
            else if(sizeBox.getSelectedItem() == "Large")
            {
                baseCost = 16;
                receipt.append("\t$" + baseCost);
                for (JCheckBox checkedBox : toppingComboBox)
                {
                    if(checkedBox.isSelected())
                    {
                        receipt.append("\n" + checkedBox.getText() + " \t\t$" + 1.00);
                        ingredientCost+=1;
                    }
                }
                
                subTotal=baseCost+ingredientCost;
                receipt.append("\n\nSub-total:\t\t$" + subTotal);
                tax = 0.07*subTotal;
                receipt.append("\n\nTax:\t\t$" + String.format("%.2f",tax));
                receipt.append("\n------------------------------------------------------");
                totalCost=subTotal+tax;
                receipt.append("\n\nTotal:\t\t$" + totalCost);
            }
            else
            {
                baseCost = 20;
                receipt.append("\t$" + baseCost);
                for (JCheckBox checkedBox : toppingComboBox)
                {
                    if(checkedBox.isSelected())
                    {
                        receipt.append("\n" + checkedBox.getText() + " \t\t$" + 1.00);
                        ingredientCost+=1;
                    }
                }
                
                subTotal=baseCost+ingredientCost;
                receipt.append("\n\nSub-total:\t\t$" + subTotal);
                tax = 0.07*subTotal;
                receipt.append("\n\nTax:\t\t$" + String.format("%.2f",tax));
                receipt.append("\n------------------------------------------------------");
                totalCost=subTotal+tax;
                receipt.append("\n\nTotal:\t\t$" + totalCost);
            }
        }
        else
        {
            receipt.append("Regular & " + sizeBox.getSelectedItem());
            if(sizeBox.getSelectedItem() == "Small")
            {
                baseCost = 8.00;
                receipt.append("\t" + "$" + baseCost);
                for (JCheckBox checkedBox : toppingComboBox)
                {
                    if(checkedBox.isSelected())
                    {
                        receipt.append("\n" + checkedBox.getText() + " \t\t$" + 1.00);
                        ingredientCost+=1;
                    }
                }
                
                subTotal=baseCost+ingredientCost;
                receipt.append("\n\nSub-total:\t\t$" + subTotal);
                tax = 0.07*subTotal;
                receipt.append("\n\nTax:\t\t$" + String.format("%.2f",tax));
                receipt.append("\n------------------------------------------------------");
                totalCost=subTotal+tax;
                receipt.append("\n\nTotal:\t\t$" + totalCost);
            }
            else if(sizeBox.getSelectedItem() == "Medium")
            {
                baseCost = 12;
                receipt.append("\t$" + baseCost);
                for (JCheckBox checkedBox : toppingComboBox)
                {
                    if(checkedBox.isSelected())
                    {
                        receipt.append("\n" + checkedBox.getText() + " \t\t$" + 1.00);
                        ingredientCost+=1;
                    }
                }
                
                subTotal=baseCost+ingredientCost;
                receipt.append("\n\nSub-total:\t\t$" + subTotal);
                tax = 0.07*subTotal;
                receipt.append("\n\nTax:\t\t$" + String.format("%.2f",tax));
                receipt.append("\n------------------------------------------------------");
                totalCost=subTotal+tax;
                receipt.append("\n\nTotal:\t\t$" + totalCost);
            }
            else if(sizeBox.getSelectedItem() == "Large")
            {
                baseCost = 16;
                receipt.append("\t$" + baseCost);
                for (JCheckBox checkedBox : toppingComboBox)
                {
                    if(checkedBox.isSelected())
                    {
                        receipt.append("\n" + checkedBox.getText() + " \t\t$" + 1.00);
                        ingredientCost+=1;
                    }
                }
                
                subTotal=baseCost+ingredientCost;
                receipt.append("\n\nSub-total:\t\t$" + subTotal);
                tax = 0.07*subTotal;
                receipt.append("\n\nTax:\t\t$" + String.format("%.2f",tax));
                receipt.append("\n------------------------------------------------------");
                totalCost=subTotal+tax;
                receipt.append("\n\nTotal:\t\t$" + totalCost);
            }
            else
            {
                baseCost = 20;
                receipt.append("\t$" + baseCost);
                for (JCheckBox checkedBox : toppingComboBox)
                {
                    if(checkedBox.isSelected())
                    {
                        receipt.append("\n" + checkedBox.getText() + " \t\t$" + 1.00);
                        ingredientCost+=1;
                    }
                }
                
                subTotal=baseCost+ingredientCost;
                receipt.append("\n\nSub-total:\t\t$" + subTotal);
                tax = 0.07*subTotal;
                receipt.append("\n\nTax:\t\t$" + String.format("%.2f",tax));
                receipt.append("\n------------------------------------------------------");
                totalCost=subTotal+tax;
                receipt.append("\n\nTotal:\t\t$" + totalCost);
            }
            receipt.append("\n===============================");
        }
        orderTextArea.setText(String.valueOf(receipt));
    }
    private void clearOrder(JComboBox<String> sizeComboBox, JCheckBox[] toppingCheckboxes, JTextArea orderTextArea) {

        sizeComboBox.setSelectedIndex(0);
        for (JCheckBox checkbox : toppingCheckboxes) {
            checkbox.setSelected(false);
        }
        regular.setSelected(true);
        orderTextArea.setText("");
    }
}