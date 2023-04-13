using System;
using System.Collections.Generic;
using System.Net.Http;
using CuaHangBanDienThoai.Models;
using CuaHangBanDienThoai.Utils;

namespace CuaHangBanDienThoai.BUS
{
    public class HangAPI
    {
        string baseUrl = Common.baseUrl;
        HttpClient client;
        public HangAPI()
        {
            client = new HttpClient();
            client.BaseAddress = new Uri(baseUrl);
        }

        public List<Hang> GetHangs()
        {
            List<Hang> hangs = new List<Hang>();
            var res = client.GetAsync("api/Hangs");
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                hangs = rs.Content.ReadAsAsync<List<Hang>>().Result;
            }

            return hangs;
        }

        public Hang GetHang(int id)
        {
            Hang hang = new Hang();
            var res = client.GetAsync($"api/Hangs/{id}");
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                hang = rs.Content.ReadAsAsync<Hang>().Result;
            }

            return hang;
        }

        public int AddHang(Hang hang)
        {
            int kq = -1;
            var res = client.PostAsJsonAsync("api/Hangs", hang);
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                kq = rs.Content.ReadAsAsync<int>().Result;
            }

            return kq;
        }
        public int UpdateHang(Hang hang)
        {
            int kq = -1;
            var res = client.PutAsJsonAsync($"api/Hangs/{hang.MaHang}", hang);
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                kq = rs.Content.ReadAsAsync<int>().Result;
            }

            return kq;
        }
        public int DeleteHang(int id)
        {
            int kq = -1;
            var res = client.DeleteAsync($"api/Hangs/{id}");
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