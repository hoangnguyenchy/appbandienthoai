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
    public class SanPhamsController : ApiController
    {
        private CuaHangDienThoaiEntities db = new CuaHangDienThoaiEntities();


        [Route("api/SanPhams/GetSanPhams")]
        public IEnumerable<GetSanPhams_Result> GetSanPhams()
        {
            return db.GetSanPhams().ToList();
        }
      
        [Route("api/SanPhams/GetSanPhamsHang")]
        public IEnumerable<GetSanPhamTheoHang_Result> GetSPTheoHang(int MaHang)
        {
            return db.GetSanPhamTheoHang(MaHang).ToList();
        }

        [Route("api/SanPhams/GetSanPhamResult")]
        public IHttpActionResult GetSanPhamResult(int id)
        {
            GetSanPhamID_Result sanPham = db.GetSanPhamID(id).FirstOrDefault();
            if (sanPham == null)
            {
                return NotFound();
            }

            return Ok(sanPham);
        }
        [Route("api/SanPhams/GetSanPham")]
        public IHttpActionResult GetSanPham(int id)
        {
            SanPham sanPham = db.SanPhams.Find(id);
            if (sanPham == null)
            {
                return NotFound();
            }

            return Ok(sanPham);
        }
        [Route("api/SanPhams/PutSanPham")]
        public IHttpActionResult PutSanPham(SanPham sanPham)
        {

            db.Entry(sanPham).State = EntityState.Modified;
            return Ok(db.SaveChanges());

        }
        [Route("api/SanPhams/PostSanPham")]
        // POST: api/SanPhams
        [ResponseType(typeof(SanPham))]
        public IHttpActionResult PostSanPham(SanPham sanPham)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.SanPhams.Add(sanPham);
            return Ok(db.SaveChanges());


        }

        [Route("api/SanPhams/DeleteSanPham")]
        [ResponseType(typeof(SanPham))]
        public IHttpActionResult DeleteSanPham(int id)
        {
            SanPham sanPham = db.SanPhams.Find(id);
            if (sanPham == null)
            {
                return NotFound();
            }

            db.SanPhams.Remove(sanPham);
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

        private bool SanPhamExists(int id)
        {
            return db.SanPhams.Count(e => e.MaSP == id) > 0;
        }
    }
}