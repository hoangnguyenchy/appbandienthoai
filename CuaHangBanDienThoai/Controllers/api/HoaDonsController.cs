using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using CuaHangBanDienThoai.Models;

namespace CuaHangBanDienThoai.Controllers.api
{
    public class HoaDonsController : ApiController
    {
        private CuaHangDienThoaiEntities db = new CuaHangDienThoaiEntities();


        [Route("api/HoaDons/GetHoaDons")]
        public IEnumerable<GetHoaDons_Result> GetHoaDons()
        {
            return db.GetHoaDons().ToList();
        }
        [Route("api/HoaDons/GetHoaDonsTaiKhoan")]
        public IEnumerable<GetHoaDonsTaiKhoan_Result> GetHoaDonsTaiKhoan(string TenTK)
        {
            return db.GetHoaDonsTaiKhoan(TenTK).ToList();
        }

        [Route("api/HoaDons/GetHoaDonResult")]
        public IHttpActionResult GetHoaDon_Result(int id)
        {
            GetHoaDons_Result hoaDon = db.GetHoaDons().ToList().FirstOrDefault(x => x.MaHD == id);
            if (hoaDon == null)
            {
                return NotFound();
            }

            return Ok(hoaDon);
        }
        [Route("api/HoaDons/GetHoaDon")]
        public IHttpActionResult GetHoaDon(int id)
        {
            HoaDon hoaDon = db.HoaDons.ToList().FirstOrDefault(x => x.MaHD == id);
            if (hoaDon == null)
            {
                return NotFound();
            }

            return Ok(hoaDon);
        }

        [Route("api/HoaDons/PutHoaDon")]
        public IHttpActionResult PutHoaDon(HoaDon hoaDon)
        {
            db.Entry(hoaDon).State = EntityState.Modified;
            return Ok(db.SaveChanges());
        }

        [Route("api/HoaDons/PostHoaDon")]
        public IHttpActionResult PostHoaDon(HoaDon hoaDon)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.HoaDons.Add(hoaDon);
            return Ok(db.SaveChanges());


        }
        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool HoaDonExists(int id)
        {
            return db.HoaDons.Count(e => e.MaHD == id) > 0;
        }
    }
}