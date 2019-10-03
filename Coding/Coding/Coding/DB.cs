using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using System.Data.Sql;
using System.Data;
using System.Data.SqlClient;


namespace Coding
{
    
    class DB
    {
        String str = clas_connection.Conn;
       
        public DB()
        {
           
            sc = new SqlConnection(str);
            sc.Open();
        }
        SqlConnection sc;
        public bool chek(String qury)
        {
            SqlDataAdapter s = new SqlDataAdapter();
            s.SelectCommand = new SqlCommand(qury,sc);
            DataTable ds = new DataTable();
            s.SelectCommand.ExecuteNonQuery();
            s.Fill(ds);
            int num = ds.Rows.Count;
            if (num < 1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        public DataTable select(string selectqury)
        {
            SqlDataAdapter s = new SqlDataAdapter();
            s.SelectCommand = new SqlCommand(selectqury, sc);
            DataTable ds = new DataTable();
            //s.SelectCommand.ExecuteNonQuery();
            s.Fill(ds);
            return ds;
        }
        public int insert(string Insertqury)
        {
            //try{
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = Insertqury;
            cmd.Connection = sc;
        
                
               int effectedRows=cmd.ExecuteNonQuery();
                return effectedRows;
                
          //  }
           // catch (Exception ex)
            //{
              //  return 0;
            //}
           //     return 0;
        }
        public int update(string Updatequry)
        {

            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = Updatequry;
            cmd.Connection = sc;


            int effectedRows = cmd.ExecuteNonQuery();
            return effectedRows;
        
        
        }
        public int delete(string deletequry)
        {
            SqlDataAdapter s = new SqlDataAdapter();
            s.DeleteCommand = new SqlCommand(deletequry, sc);
         int effectedRows =    s.DeleteCommand.ExecuteNonQuery();
         return effectedRows;
        }
    }
        
}
