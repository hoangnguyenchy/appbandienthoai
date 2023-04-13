using CuaHangBanDienThoai.Models;
using CuaHangBanDienThoai.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;

namespace CuaHangBanDienThoai.BUS
{
    public class TaiKhoanAPI
    {
        string baseUrl = Common.baseUrl;
        HttpClient client;
        public TaiKhoanAPI()
        {
            client = new HttpClient();
            client.BaseAddress = new Uri(baseUrl);
        }
        public IEnumerable<TaiKhoan> GetTaiKhoans()
        {
            List<TaiKhoan>taiKhoans = new List<TaiKhoan>();
            var res = client.GetAsync("api/TaiKhoans/GetTaiKhoans");
            res.Wait();
            var result = res.Result;
            if (result.IsSuccessStatusCode)
            {
                taiKhoans = result.Content.ReadAsAsync<List<TaiKhoan>>().Result;
            }
            return taiKhoans;
        }
        public TaiKhoan GetTaiKhoan(string id)
        {
            TaiKhoan taiKhoan=null;
            var res=client.GetAsync($"api/TaiKhoans/GetTaiKhoan?id={id}");
            res.Wait();
            var result = res.Result;
            if (result.IsSuccessStatusCode)
            {
                taiKhoan = result.Content.ReadAsAsync<TaiKhoan>().Result;
            }
            return taiKhoan;
        }
        public int DeleteTaiKhoan(string id)
        {
            int kq = -1;
            var res = client.DeleteAsync($"api/TaiKhoans/DeleteTaiKhoan?id={id}");
            res.Wait();
            var result = res.Result;
            if (result.IsSuccessStatusCode)
            {
                kq = result.Content.ReadAsAsync<int>().Result;
            }
            return kq;
        }
    }
}