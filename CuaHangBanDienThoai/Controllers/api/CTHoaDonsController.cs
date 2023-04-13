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
    public class CTHoaDonsController : ApiController
    {
        private CuaHangDienThoaiEntities db = new CuaHangDienThoaiEntities();

        // GET: api/CTHoaDons
        [Route("api/CTHoaDons/GetCTHoaDons")]
        public IEnumerable<GetCTHoaDon_Result> GetCTHoaDons(int idHD)
        {
            return db.GetCTHoaDon(idHD);
        }

        [Route("api/CTHoaDons/GetCTHoaDon")]
        public IHttpActionResult GetCTHoaDon(int id)
        {
            CTHoaDon cTHoaDon = db.CTHoaDons.Find(id);
            if (cTHoaDon == null)
            {
                return NotFound();
            }

            return Ok(cTHoaDon);
        }

        [Route("api/CTHoaDons/PutCTHoaDon")]
        public IHttpActionResult PutCTHoaDon(CTHoaDon cTHoaDon)
        {
            db.Entry(cTHoaDon).State = EntityState.Modified;
            return Ok(db.SaveChanges());
        }

        // POST: api/CTHoaDons
        [Route("api/CTHoaDons/PostCTHoaDon")]
        public IHttpActionResult PostCTHoaDon(CTHoaDon cTHoaDon)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            int lastIndex = db.HoaDons.Count() - 1;
            var model = db.HoaDons.ToList()[lastIndex];
            cTHoaDon.MaHD = model.MaHD;
            db.CTHoaDons.Add(cTHoaDon);
            return Ok(db.SaveChanges());
        }

        // DELETE: api/CTHoaDons/5
        [Route("api/CTHoaDons/DeleteCTHoaDon")]
        public IHttpActionResult DeleteCTHoaDon(int id)
        {
            CTHoaDon cTHoaDon = db.CTHoaDons.Find(id);
            if (cTHoaDon == null)
            {
                return NotFound();
            }

            db.CTHoaDons.Remove(cTHoaDon);
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

        private bool CTHoaDonExists(int id)
        {
            return db.CTHoaDons.Count(e => e.MaCTHD == id) > 0;
        }
    }
}