package Posttest7;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormDataBuku extends javax.swing.JFrame { //method untuk menghubungkan form dengan database yang akan digunakan
    private DefaultTableModel model;                   
    private Connection con = koneksi.getConnection();
    private Statement stt;
    private ResultSet rss;
    int baris;
    private boolean k = true;
    
    public FormDataBuku() {
        initComponents();
    }
    private void InitTable(){ //penamaan
        model = new DefaultTableModel(); //method yang digunakan untuk mendeklarasikan kolom yang akan dihubungkan ke database
        model.addColumn("Id Buku");       //dan yang akan ditampilkan pada tabel
        model.addColumn("Judul");
        model.addColumn("Penulis");
        model.addColumn("Harga");
        
        jTable2.setModel(model);
    }
    
    private void TampilData(){ // method untuk menampilkan data dari tabel buku
        try{
            String sqlshow = "SELECT * FROM buku"; // query untuk menampilkan data
            stt = con.createStatement();
            rss = stt.executeQuery(sqlshow);
            while(rss.next()){
                Object[] o = new Object[4];
                o[0] = rss.getString("id");
                o[1] = rss.getString("judul");
                o[2] = rss.getString("penulis");
                o[3] = rss.getString("harga");
                model.addRow(o);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
    }
    }
    
    private void TambahData(String judul, String penulis, String harga){ // method untuk menambahkan data ada tabel buku
        try{
            String sqlinsert =
                    "INSERT INTO buku VALUES (NULL,'"+ //query untuk menambahkan data
                    judul+"','"+penulis+"','"+harga+"')";
            stt = con.createStatement();
            stt. executeUpdate(sqlinsert);
            model.addRow(new Object [] {judul,penulis,harga}); //menambahkan data baru sesuai object yang dibuat pada method ValidasiData
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    private void HapusData(String id, int baris){ // method untuk menghapus data dari tabel buku
        try{
             String sqldelete =
                    "DELETE FROM buku WHERE id = '"+id+"'"; // query untuk menghapus data
            stt = con.createStatement();
            stt. executeUpdate(sqldelete);
            model.removeRow(baris);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void UbahData(String id, String judul, String penulis, String harga){ // method untuk mengubah isi tabel buku
        try{
             String sqlre = // query yang digunakan untuk mengubah isi tabel
                    "UPDATE buku SET judul='"+judul+"',"+"penulis='"+penulis+"',harga='"+harga+"' where id='"+id+"'";
            stt = con.createStatement();
            stt. executeUpdate(sqlre);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
     private void SearchbyId(){ // method pencarian berdasarkan id buku
        try{
            String sqlsi = "SELECT *FROM buku WHERE id='"+txtCari.getText()+"'"; // query pencarian berdasarkan id
            stt = con.createStatement();
            rss = stt.executeQuery(sqlsi);
            while(rss.next()){
                Object[] o = new Object[4];
                o[0] = rss.getInt("id");
                o[1] = rss.getString("judul");
                o[2] = rss.getString("penulis");
                o[3] = rss.getString("harga");
                model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void SearchbyJudul(){ // method pencarian berdasarkan judul buku
        try{
            String sqlsj = "SELECT *FROM buku WHERE judul='"+txtCari.getText()+"'"; // query pencarian berdasarkan judul
            stt = con.createStatement();
            rss = stt.executeQuery(sqlsj);
            while(rss.next()){
                Object[] o = new Object[4];
                o[0] = rss.getInt("id");
                o[1] = rss.getString("judul");
                o[2] = rss.getString("penulis");
                o[3] = rss.getString("harga");
                model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
     private void SearchbyPenulis(){ // method pencarian berdasarkan penulis buku
        try{
            String sqlsp = "SELECT *FROM buku WHERE judul='"+txtCari.getText()+"'"; // query pencarian berdasarkan penulis
            stt = con.createStatement();
            rss = stt.executeQuery(sqlsp);
            while(rss.next()){
                Object[] o = new Object[4];
                o[0] = rss.getInt("id");
                o[1] = rss.getString("judul");
                o[2] = rss.getString("penulis");
                o[3] = rss.getString("harga");
                model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
     
      private void SearchbyHarga(){ // method pencarian berdasarkan harga buku
        try{
            String sqlsh = "SELECT *FROM buku WHERE judul='"+txtCari.getText()+"'";// query pencarian berdasarkan harga
            stt = con.createStatement();
            rss = stt.executeQuery(sqlsh);
            while(rss.next()){
                Object[] o = new Object[4];
                o[0] = rss.getInt("id");
                o[1] = rss.getString("judul");
                o[2] = rss.getString("penulis");
                o[3] = rss.getString("harga");
                model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

      private void ValidasiData(String judul, String penulis, String harga){
          try{
              String sqlinsert = "SELECT * FROM buku";//melihat dah mengambil data dari tabel yang ada
              stt = con.createStatement();
              rss = stt.executeQuery(sqlinsert);
              while(rss.next()){
                  Object [] o = new Object [2];
                  o[0] = rss.getString("judul").toLowerCase();
                  o[1] = rss.getString("penulis").toLowerCase();
                  
                  if(o[0].equals(judul.toLowerCase()) && o[1].equals(penulis.toLowerCase())){// jika data yang diinputkan sama
                  JOptionPane.showMessageDialog(null, "Data sudah ada");//dengan data yang ada dalam tabel, maka akan menampilkan                 k = false;
                  k=false;//pesan bahwa data sudah ada
                  break;
              }
              }
              if(k==true){//jika data yang diinputkan tidak sama, maka data akan terisi ke tabel dengan menjalankan method TambahData
                  TambahData(judul,penulis,harga);
              }
          }catch(Exception e){
              System.out.println(e.getMessage());
          }
      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtJudul = new javax.swing.JTextField();
        comboPenulis = new javax.swing.JComboBox();
        txtHarga = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Judul");

        jLabel2.setText("Penulis");

        jLabel3.setText("Harga");

        txtJudul.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtJudulCaretUpdate(evt);
            }
        });

        comboPenulis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih", "Tere Liye", "W.S Rendra", "Felix Siauw", "Asma Nadia", "Dewi Lestari" }));

        txtHarga.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHargaCaretUpdate(evt);
            }
        });

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ubah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Keluar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Buku", "Judul", "Penulis", "Harga"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih", "Id", "Judul", "Penulis", "Harga" }));

        jButton5.setText("Cari");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setText("by");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("FORM DATA BUKU");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(comboPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(txtJudul)
                                .addComponent(txtHarga)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton4))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(128, 128, 128))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        InitTable(); //memanggil method yang telah dideklarasikan diawal
        TampilData(); //tampilan awal akan menampilkan tabel dan data yang ada
    }//GEN-LAST:event_formComponentShown

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here: 
        if(txtJudul.getText().equals("") || txtHarga.getText().equals("")){//seleksi untuk menjalankan method ValidasiData
            JOptionPane.showMessageDialog(null, "Data Lengkap");
            txtJudul.requestFocus();
        }
        String judul = txtJudul.getText(); // mengambil nilai dari field yang telah diisi lalu menjalankan method TambahData
        String penulis = comboPenulis.getSelectedItem().toString();  
        String harga = txtHarga.getText();
        
        ValidasiData(judul,penulis,harga);
        InitTable();
        TampilData();
        
        txtHarga.setText("");
        comboPenulis.setSelectedItem("Pilih");
        txtJudul.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int baris = jTable2.getSelectedRow(); // menjalankan method UbahData untuk mengubah isi data yang sudah ada pada tabel
                                                //menggantikan nilai yang ada dengan nilai yang baru
        jTable2.setValueAt(txtJudul.getText(),baris,1);
        jTable2.setValueAt(comboPenulis.getSelectedItem(),baris,2);
        jTable2.setValueAt(txtHarga.getText(),baris,3);
        
        String id = jTable2.getValueAt(baris,0).toString();
        String judul = jTable2.getValueAt(baris,1).toString();
        String penulis = jTable2.getValueAt(baris,2).toString();
        String harga = jTable2.getValueAt(baris,3).toString();
        UbahData(id,judul,penulis,harga);
        
        txtJudul.setText(judul);
        comboPenulis.setSelectedItem(penulis);
        txtHarga.setText(harga);
        
        JOptionPane.showMessageDialog(null,"Data berhasil diubah");
        
        txtHarga.setText("");
        comboPenulis.setSelectedItem("Pilih");
        txtJudul.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{ 
            int baris = jTable2.getSelectedRow(); // method untuk menghapus isi tabel dengan menjalankan method HapusData yang telah dideklarasikan diawal
            String id = jTable2.getValueAt(baris,0).toString();
            HapusData(id,baris);
            JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Data gagal dihapus");
        }
        txtHarga.setText("");
        comboPenulis.setSelectedItem("Pilih");
        txtJudul.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:    
        int baris = jTable2.getSelectedRow(); //menampilkan data pada tabel baik saat terjadi perubahan atau apapun
                                                //data akan dimuat ulang untuk ditampilkan lagi
        String judul = jTable2.getValueAt(baris,1).toString();
        String penulis = jTable2.getValueAt(baris,2).toString();
        String harga = jTable2.getValueAt(baris,3).toString();
        
        txtJudul.setText(judul);
        comboPenulis.setSelectedItem(penulis);
        txtHarga.setText(harga);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.exit(0); // method untuk keluar dari program
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(txtCari.getText().equals("")){ // method pencarian berdasarkan pilihan pencaraian yang kita pilih
            JOptionPane.showMessageDialog(null,"Isi pencarian");
        }
        else{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            if(jComboBox1.getSelectedItem().equals("Id")){ // berdasarkan id
                SearchbyId();
            }
            else if(jComboBox1.getSelectedItem().equals("Judul")){ // berdasarkan judul
                SearchbyJudul();   
            }
            else if(jComboBox1.getSelectedItem().equals("Penulis")){ // berdasarkan penulis
                SearchbyPenulis();   
            }
            else if(jComboBox1.getSelectedItem().equals("Harga")){ // bedasarkan harga
                SearchbyHarga();   
            }
        }
        txtCari.setText("");
        jComboBox1.setSelectedItem("Pilih");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtJudulCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtJudulCaretUpdate
        // TODO add your handling code here:
        if(txtJudul.getText().equals("")||txtHarga.getText().equals("")){ // digunakan agar field tidak boleh kosong saat ingin menyimpan data
            jButton1.setEnabled(false);
        }
        else{
            jButton1.setEnabled(true);
        }
    }//GEN-LAST:event_txtJudulCaretUpdate

    private void txtHargaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHargaCaretUpdate
        // TODO add your handling code here:
         if(txtJudul.getText().equals("")||txtHarga.getText().equals("")){ // digunakan agar field tidak boleh kosong saat ingin menyimpan data
            jButton1.setEnabled(false);
        }
        else{
            jButton1.setEnabled(true);
        }
    }//GEN-LAST:event_txtHargaCaretUpdate

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        txtHarga.setText(""); // ketika program baru dibuka, semua field akan diset kosong
        txtJudul.setText("");
        txtCari.setText("");
        comboPenulis.setSelectedItem("Pilih");
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDataBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboPenulis;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJudul;
    // End of variables declaration//GEN-END:variables
}
