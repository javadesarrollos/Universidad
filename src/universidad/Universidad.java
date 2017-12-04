package universidad;


        import java.awt.EventQueue;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.SQLException;

        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.JTextField;

        import java.awt.Font;
        import java.sql.Statement;

public class Universidad{

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Universidad window = new Universidad();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Universidad() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 301);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblN = new JLabel("Nombre");
        lblN.setBounds(19, 24, 61, 16);
        frame.getContentPane().add(lblN);

        JLabel lblMa = new JLabel("Mat");
        lblMa.setBounds(19, 70, 61, 16);
        frame.getContentPane().add(lblMa);

        JLabel lblTr = new JLabel("");
        lblTr.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblTr.setBounds(92, 234, 130, 29);
        frame.getContentPane().add(lblTr);

        textField = new JTextField();
        textField.setBounds(92, 24, 130, 21);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(92, 70, 130, 21);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);



        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/universidad", "root", "root");

                    Statement statement = conexion.createStatement();

                    String nombre = textField.getText();
                    ((java.sql.Statement)statement).executeUpdate("insert into alumnos(nombre) value('"+nombre+"')");
                    conexion.close();

                }catch (ClassNotFoundException o) {
                    o.printStackTrace();

                }catch (SQLException l) {
                    l.printStackTrace();
                }
            }
        });
        btnAgregar.setBounds(19, 121, 117, 29);
        frame.getContentPane().add(btnAgregar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(textField_1.getText());
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/universidad", "root", "root");

                    Statement statement = (Statement) conexion.createStatement();
                    ResultSet resultSet = statement.executeQuery("select nombre from alumnos where matricula="+id);

                    if(resultSet.next() == true) {
                        lblTr.setText(resultSet.getString("nombre"));
                    }


                }catch (ClassNotFoundException e1) {
                    e1.printStackTrace();

                }catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnBuscar.setBounds(19, 151, 117, 29);
        frame.getContentPane().add(btnBuscar);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(textField_1.getText());
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/universidad", "root", "root");

                    Statement statement = (Statement) conexion.createStatement();


                    String query = "Delete from alumnos where matricula="+id;
                    statement.executeUpdate(query);

                    conexion.close();


                }catch (ClassNotFoundException e1) {
                    e1.printStackTrace();

                }catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnBorrar.setBounds(19, 180, 117, 29);
        frame.getContentPane().add(btnBorrar);


    }

}
