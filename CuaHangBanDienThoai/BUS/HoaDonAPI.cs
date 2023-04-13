using System;
using System.Collections.Generic;
using System.Net.Http;
using CuaHangBanDienThoai.Models;
using CuaHangBanDienThoai.Utils;

namespace CuaHangBanDienThoai.BUS
{
    public class HoaDonAPI
    {
        string baseUrl = Common.baseUrl;
        HttpClient client;
        public HoaDonAPI()
        {
            client = new HttpClient();
            client.BaseAddress = new Uri(baseUrl);
        }

        public List<GetHoaDons_Result> GetHoaDonsResults()
        {
            List<GetHoaDons_Result> hoaDonsResults = new List<GetHoaDons_Result>();
            var res = client.GetAsync("api/HoaDons/GetHoaDons");
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                hoaDonsResults = rs.Content.ReadAsAsync<List<GetHoaDons_Result>>().Result;
            }

            return hoaDonsResults;
        }

        public GetHoaDons_Result GetHoaDonsResult(int id)
        {
            GetHoaDons_Result getHoaDonsResult = new GetHoaDons_Result();
            var res = client.GetAsync($"api/HoaDons/GetHoaDonResult?id={id}");
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                getHoaDonsResult = rs.Content.ReadAsAsync<GetHoaDons_Result>().Result;
            }

            return getHoaDonsResult;
        }

        public HoaDon GetHoaDon(int id)
        {
            HoaDon hoaDon = new HoaDon();
            var res = client.GetAsync($"api/HoaDons/GetHoaDon?id={id}");
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                hoaDon = rs.Content.ReadAsAsync<HoaDon>().Result;
            }

            return hoaDon;
        }

        public int UpdateHoaDon(HoaDon hoaDon)
        {
            int kq = -1;
            var res = client.PutAsJsonAsync("api/HoaDons/PutHoaDon", hoaDon);
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