using CuaHangBanDienThoai.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using CuaHangBanDienThoai.Models;

namespace CuaHangBanDienThoai.BUS
{
    public class SanPhamAPI
    {
        string baseUrl = Common.baseUrl;
        HttpClient client;
        public SanPhamAPI()
        {
            client = new HttpClient();
            client.BaseAddress = new Uri(baseUrl);
        }

        public List<GetSanPhams_Result> GetSanPhamsResults()
        {
            List<GetSanPhams_Result> sanPhamsResults = new List<GetSanPhams_Result>();
            var res = client.GetAsync("api/SanPhams/GetSanPhams");
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                sanPhamsResults = rs.Content.ReadAsAsync<List<GetSanPhams_Result>>().Result;
            }

            return sanPhamsResults;
        }

        public GetSanPhamID_Result GetSanPhamsResult(int id)
        {
            GetSanPhamID_Result sanPhamsResult = new GetSanPhamID_Result();
            var res = client.GetAsync($"api/SanPhams/GetSanPhamResult?id={id}");
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                sanPhamsResult = rs.Content.ReadAsAsync<GetSanPhamID_Result>().Result;
            }

            return sanPhamsResult;
        }

        public SanPham GetSanPham(int id)
        {
            SanPham sanPham = new SanPham();
            var res = client.GetAsync($"api/SanPhams/GetSanPham?id={id}");
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                sanPham = rs.Content.ReadAsAsync<SanPham>().Result;
            }

            return sanPham;
        }

        public int UpdateSanPham(SanPham sanPham)
        {
            int kq = -1;
            var res = client.PutAsJsonAsync("api/SanPhams/PutSanPham", sanPham);
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                kq = rs.Content.ReadAsAsync<int>().Result;
            }

            return kq;
        }
        public int AddSanPham(SanPham sanPham)
        {
            int kq = -1;
            var res = client.PostAsJsonAsync("api/SanPhams/PostSanPham", sanPham);
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                kq = rs.Content.ReadAsAsync<int>().Result;
            }

            return kq;
        }

        public int DeleteSanPham(int id)
        {
            int kq = -1;
            var res = client.DeleteAsync($"api/SanPhams/DeleteSanPham?id={id}");
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                kq = rs.Content.ReadAsAsync<int>().Result;
            }

            return kq;
        }
    }
}