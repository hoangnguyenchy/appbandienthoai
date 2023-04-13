using CuaHangBanDienThoai.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace CuaHangBanDienThoai.Controllers.api
{
    public class HinhAnhsController : ApiController
    {
        CuaHangDienThoaiEntities db = new CuaHangDienThoaiEntities();
        [Route("api/HinhAnh/GetHinhAnh")]
        public IEnumerable<HinhAnh> Get(int MaSP)
        {
            return db.HinhAnhs.Where(x => x.MaSP == MaSP);
        }
    }
}
