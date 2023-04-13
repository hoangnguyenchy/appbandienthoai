using System;
using System.Collections.Generic;
using System.Net.Http;
using CuaHangBanDienThoai.Models;
using CuaHangBanDienThoai.Utils;

namespace CuaHangBanDienThoai.BUS
{
    public class CTHoaDonAPI
    {
        string baseUrl = Common.baseUrl;
        HttpClient client;
        public CTHoaDonAPI()
        {
            client = new HttpClient();
            client.BaseAddress = new Uri(baseUrl);
        }

        public List<GetCTHoaDon_Result> GetCtHoaDonResults(int id)
        {
            List<GetCTHoaDon_Result> GetCTHoaDon_Results = new List<GetCTHoaDon_Result>();
            var res = client.GetAsync($"api/CTHoaDons/GetCTHoaDons?idHD={id}");
            res.Wait();
            var rs = res.Result;
            if (rs.IsSuccessStatusCode)
            {
                GetCTHoaDon_Results = rs.Content.ReadAsAsync<List<GetCTHoaDon_Result>>().Result;
            }

            return GetCTHoaDon_Results;
        }

    }
}