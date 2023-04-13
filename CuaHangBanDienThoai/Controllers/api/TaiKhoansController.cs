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
    public class TaiKhoansController : ApiController
    {
        private CuaHangDienThoaiEntities db = new CuaHangDienThoaiEntities();


        [Route("api/TaiKhoans/GetTaiKhoans")]
        public IEnumerable<TaiKhoan> GetTaiKhoans()
        {
            return db.TaiKhoans.ToList();
        }
        [Route("api/TaiKhoans/GetTaiKhoan")]
        public IHttpActionResult GetTaiKhoan(string id)
        {
            TaiKhoan taiKhoan = db.TaiKhoans.Find(id);
            if (taiKhoan == null)
            {
                return NotFound();
            }

            return Ok(taiKhoan);
        }
        [HttpGet]
        [Route("api/TaiKhoans/DangNhap")]
        public bool DangNhap(string TenTK,string MatKhau)
        {
            var tk = db.TaiKhoans.FirstOrDefault(x => x.TenTK.Equals(TenTK) && x.MatKhau.Equals(MatKhau));
            if (tk == null) return false;
            return true;
        }
        [Route("api/TaiKhoans/PutTaiKhoan")]
        public IHttpActionResult PutTaiKhoan(TaiKhoan taiKhoan)
        {


            db.Entry(taiKhoan).State = EntityState.Modified;


            return Ok(db.SaveChanges());

        }

        [Route("api/TaiKhoans/PostTaiKhoan")]
        public IHttpActionResult PostTaiKhoan(TaiKhoan taiKhoan)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.TaiKhoans.Add(taiKhoan);

            return Ok(db.SaveChanges());

        }

        [Route("api/TaiKhoans/DeleteTaiKhoan")]
       
        public IHttpActionResult DeleteTaiKhoan(string id)
        {
            TaiKhoan taiKhoan = db.TaiKhoans.Find(id);
            if (taiKhoan == null)
            {
                return NotFound();
            }
            db.TaiKhoans.Remove(taiKhoan);
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

        private bool TaiKhoanExists(string id)
        {
            return db.TaiKhoans.Count(e => e.TenTK == id) > 0;
        }
    }
}