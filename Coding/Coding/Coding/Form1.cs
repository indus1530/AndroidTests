using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Coding
{
    public partial class Form1 : Form
    {

        DB obj = new DB();

        DataTable dt;
        string query;
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            string text = textBox1.Text;



            //var index = dataGridView1.Rows.Count;

            dataGridView1.Rows.Clear();
            fun_getdata(text);

         


        }

        public  void fun_getdata(string par1)

        {

            query = "select  qc.id,qc.q_id,qkc.key_id,qc.hh_mem_info_id from " +
                  " tbl_hh_memb_q_comb qc  left join  tbl_hh_memb_q_key_comb qkc " +
                   " on qc.id=qkc.hh_info_q_comb_id " +
                   " where qkc.id is null " +

                     "and qc.q_id='" + par1 + "'";

            dt=obj.select(query);

            label3.Text = dt.Rows.Count.ToString();
            for (int i = 0; i < dt.Rows.Count; i++)
            {
                var index = dataGridView1.Rows.Add();


                dataGridView1.Rows[index].Cells[0].Value = dt.Rows[i][0].ToString();
                dataGridView1.Rows[index].Cells[1].Value = dt.Rows[i][1].ToString();
                dataGridView1.Rows[index].Cells[2].Value = dt.Rows[i][2].ToString();
                dataGridView1.Rows[index].Cells[3].Value = dt.Rows[i][3].ToString();

            }
           
            

        }




        public void fun_getdata_dependent(string par1,string par2)
        {

            query = "select  qc.id,qc.q_id,qkc.key_id from " +
                  " tbl_hh_memb_q_comb qc  left join  tbl_hh_memb_q_key_comb qkc " +
                   " on qc.id=qkc.hh_info_q_comb_id " +
                   " where hh_mem_info_id="+par2+

                     "and qc.q_id='" + par1 + "'";

            dt = obj.select(query);

            label3.Text = dt.Rows.Count.ToString();
            for (int i = 0; i < dt.Rows.Count; i++)
            {
                var index = dataGridView2.Rows.Add();


                dataGridView2.Rows[index].Cells[0].Value = dt.Rows[i][0].ToString();
                dataGridView2.Rows[index].Cells[1].Value = dt.Rows[i][1].ToString();
                dataGridView2.Rows[index].Cells[2].Value = dt.Rows[i][2].ToString();
                dataGridView2.Rows[index].Cells[3].Value = dt.Rows[i][3].ToString();

            }



        }

        private void button2_Click(object sender, EventArgs e)
        {


            string text = textBox2.Text;




            dataGridView2.Rows.Clear();
           // fun_getdata_dependent(text);

            for (int i = 0; i < dataGridView1.Rows.Count-1; i++)
            {
                string hid = dataGridView1.Rows[i].Cells[3].Value.ToString();

                fun_getdata_dependent(text, hid);

            }
        }







    }
}
