using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using CuaHangBanDienThoai.BUS;
using CuaHangBanDienThoai.Models;

namespace CuaHangBanDienThoai.Controllers
{
    public class HoaDonsController : Controller
    {
        private HoaDonAPI api = new HoaDonAPI();
        private CTHoaDonAPI CTHoaDonAPI = new CTHoaDonAPI();

        // GET: HoaDons
        public ActionResult Index()
        {
            return View(api.GetHoaDonsResults());
        }
        public ActionResult Details(int id)
        {
            return View(CTHoaDonAPI.GetCtHoaDonResults(id));
        }
    }
}
